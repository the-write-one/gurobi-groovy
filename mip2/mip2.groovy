/* Contributed by Christianna Johansen (C) 2018

   This example reads a MIP model from a file, solves it and
   prints the objective values from all feasible solutions
   generated while solving the MIP. Then it creates the fixed
   model and solves that model. */

import gurobi.*

def env = new GRBEnv()
def model = new GRBModel(env, "../data/coins.lp")

model.optimize();

def status = model.get(GRB.IntAttr.Status);
double objval = 0;

switch (status) {
    case GRB.Status.OPTIMAL:
        objval = model.get GRB.DoubleAttr.ObjVal
        println "Optimal objective: $objval"
        break
    case GRB.Status.INF_OR_UNBD:
        println "Model is infeasible or unbounded"
        return
    case GRB.Status.INFEASIBLE:
        println "Model is infeasible or infeasible"
        return
    case GRB.Status.UNBOUNDED:
        println "Model is infeasible or unbounded"
        return
    case GRB.Status.INF_OR_UNBD:
        println "Model is infeasible or unbounded"
        return
    default:
        println "Optimization was stopped with status: $status"
        return
}

/* Iterate over the solutions and compute the objectives */
def vars = model.getVars();
model.set(GRB.IntParam.OutputFlag, 0);

println()

(0..model.get(GRB.IntAttr.SolCount) - 1).each {
    model.set GRB.IntParam.SolutionNumber, it
    double objn = vars.sum { it.get(GRB.DoubleAttr.Obj) *
            it.get(GRB.DoubleAttr.Xn) }

    println "Solution $it has objective: $objn"
}
println()
model.set(GRB.IntParam.OutputFlag, 1);

/* Create a fixed model, turn off presolve and solve */

GRBModel fixed = model.fixedModel();

fixed.set(GRB.IntParam.Presolve, 0);

fixed.optimize();

int foptimstatus = fixed.get(GRB.IntAttr.Status);

if (foptimstatus != GRB.Status.OPTIMAL) {
    println("Error: fixed model isn't optimal");
    return;
}

double fobjval = fixed.get(GRB.DoubleAttr.ObjVal);

if (Math.abs(fobjval - objval) > 1.0e-6 * (1.0 + Math.abs(objval))) {
    println("Error: objective values are different");
    return;
}

GRBVar[] fvars = fixed.getVars()
double[] x = fixed.get GRB.DoubleAttr.X, fvars
String[] vnames = fixed.get GRB.StringAttr.VarName, fvars

(0..fvars.length-1).findAll { x[it] != 0.0 }.each {
    println "${vnames[it]}: ${x[it]}"
}
