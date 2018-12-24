/* Contributed by Christianna Johansen (C) 2018

   This example creates a very simple Special Ordered Set (SOS) model.
   The model consists of 3 continuous variables, no linear constraints,
   and a pair of SOS constraints of type 1. */

import gurobi.*

def env = new GRBEnv()
def model = new GRBModel(env)

String[] names = ["x0", "x1", "x2"]    // variables
double[] ub  =   [  1,    1,    2]     // upper bounds
double[] obj =   [ -2,   -1,   -1]     // objectives

GRBVar[] x = model.addVars null, ub, obj, null, names

GRBVar[] sosv1  = [x[0], x[1]]         // SOS constraint 1: x0=0 or x1=0
double[] soswt1 = [  1,    2]          // weights: x0 = 1, x1 = 2

model.addSOS sosv1, soswt1, GRB.SOS_TYPE1

GRBVar[] sosv2  = [x[0], x[2]]         // SOS constraint 2: x0=0 or x2=0
double[] soswt2 = [  1,    2]          // weights: x0 = 1, x2 = 2

model.addSOS sosv2, soswt2, GRB.SOS_TYPE1

model.optimize()

x.each { println "${it.get(GRB.StringAttr.VarName)}: ${it.get(GRB.DoubleAttr.X)}" }

println 'Obj: ' + model.get(GRB.DoubleAttr.ObjVal)
