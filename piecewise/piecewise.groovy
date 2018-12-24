/* Contributed by Christianna Johansen (C) 2018

   This example considers the following separable, convex problem:

     minimize    f(x) - y + g(z)
     subject to  x + 2 y + 3 z <= 4
                 x +   y       >= 1
                 x,    y,    z <= 1

   where f(u) = exp(-u) and g(u) = 2 u^2 - 4 u, for all real u. It
   formulates and solves a simpler LP model by approximating f and
   g with piecewise-linear functions. Then it transforms the model
   into a MIP by negating the approximation for f, which corresponds
   to a non-convex piecewise-linear function, and solves it again. */

import gurobi.*

// Overloading operators 

GRBLinExpr.metaClass.plus {
    if (it instanceof GRBVar)     { delegate.addTerm 1.0, it } else 
    if (it instanceof GRBLinExpr) { delegate.add it          } else 
                                  { delegate.addConstant it as Double }
    delegate
}
GRBVar.metaClass.plus {
    def expr = new GRBLinExpr();
    expr.addTerm 1.0, delegate
    expr + it
}
GRBVar.metaClass.multiply {
    def expr = new GRBLinExpr();
    expr.addTerm it as Double, delegate
    expr
}

// Model definition

def f(double u) { Math.exp(-u) }
def g(double u) { 2 * u * u - 4 * u }

def env = new GRBEnv();
def model = new GRBModel(env);

double lb = 0.0, ub = 1.0  // lower and upper boundaries

def x = model.addVar lb, ub, 0.0, GRB.CONTINUOUS, "x"
def y = model.addVar lb, ub, 0.0, GRB.CONTINUOUS, "y"
def z = model.addVar lb, ub, 0.0, GRB.CONTINUOUS, "z"

model.setObjective y * -1   // objective: -y

// Add piecewise-linear objective functions for x and z

def npts = 101
double[] ptu = (0..npts - 1).collect { i -> lb + (ub - lb) * i / (npts - 1) }
double[] ptf = ptu.collect { f(it) }
double[] ptg = ptu.collect { g(it) }

model.setPWLObj x, ptu, ptf
model.setPWLObj z, ptu, ptg

model.addConstr x + y*2 + z*3, GRB.LESS_EQUAL, 4.0, "c0"

model.addConstr x + y, GRB.GREATER_EQUAL, 1.0, "c1"

// Optimize model as an LP

model.optimize();

println "IsMIP: " + model.get(GRB.IntAttr.IsMIP)

model.vars.each {
   println "${it.get(GRB.StringAttr.VarName)}: ${it.get(GRB.DoubleAttr.X)}"
}
println 'Obj: ' + model.get(GRB.DoubleAttr.ObjVal)
println()

// Negate piecewise-linear objective function for x

ptf = ptf.collect { -it }

model.setPWLObj(x, ptu, ptf);

// Optimize model as a MIP

model.optimize();

println "IsMIP: " + model.get(GRB.IntAttr.IsMIP)

model.vars.each {
   println "${it.get(GRB.StringAttr.VarName)}: ${it.get(GRB.DoubleAttr.X)}"
}
println 'Obj: ' + model.get(GRB.DoubleAttr.ObjVal)
