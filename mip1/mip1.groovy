/* Contributed by Christianna Johansen (C) 2018

   This example formulates and solves the following simple MIP model:

   maximize
         x +   y + 2 z
   subject to
         x + 2 y + 3 z <= 4
         x +   y       >= 1
   x, y, z binary
*/

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

def env = new GRBEnv()
def model = new GRBModel(env)

def x = model.addVar 0.0, 1.0, 0.0, GRB.BINARY, "x"
def y = model.addVar 0.0, 1.0, 0.0, GRB.BINARY, "y"
def z = model.addVar 0.0, 1.0, 0.0, GRB.BINARY, "z"

model.setObjective x + y + z*2, GRB.MAXIMIZE

model.addConstr x + y*2 + z*3, GRB.LESS_EQUAL,   4.0, "c0"

model.addConstr x + y,         GRB.GREATER_EQUAL, 1.0, "c1"

model.optimize();

int status = model.get(GRB.IntAttr.Status)
if (status != GRB.Status.OPTIMAL) {
    println "Status is not OPTIMAL: " + status
    return
}

model.vars.each {
   println "${it.get(GRB.StringAttr.VarName)}: ${it.get(GRB.DoubleAttr.X)}"
}
println 'Obj: ' + model.get(GRB.DoubleAttr.ObjVal)

