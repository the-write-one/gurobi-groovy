This example creates a very simple Special Ordered Set (SOS) model.
The model consists of 3 continuous variables, no linear constraints,
and a pair of SOS constraints of type 1.

* View source: [sos.groovy](https://github.com/the-write-one/gurobi-groovy/blob/master/sos/sos.groovy)
* Compare: [sos.py](https://www.gurobi.com/documentation/8.1/examples/sos_py.html),
           [Sos.java](https://www.gurobi.com/documentation/8.1/examples/sos_java.html)

## Preamble

Similar to Java, the Groovy script starts with importing the Gurobi package:
```groovy
import gurobi.*
```
and instantiating Gurobi environment and model instances:
```groovy
def env   = new GRBEnv()
def model = new GRBModel(env)
```

## Types in Variable Declarations

Groovy can derive variable types from the context,
so in many cases declaring types, such as `GRBEnv` and `GRBModel`,  can be substituted with `def`.

Exceptions to this convention include:
 * when additional readability is desired
 * declaring an array of certain types, as opposed to a `List`
 * declaring numeric variables: Groovy default numeric type is `BigDecimal`, so constants and varible passed to Gurobi API should be declared explicitely as `double`

Examples of parameter declarations for the SOS methods:
```groovy
String[] names = ["x0", "x1", "x2"]    // variables
double[] ub  =   [  1,    1,    2]     // upper bounds
double[] obj =   [ -2,   -1,   -1]     // objectives

GRBVar[] x = model.addVars null, ub, obj, null, names

GRBVar[] sosv1  = [x[0], x[1]]         // SOS constraint 1: x0=0 or x1=0
double[] soswt1 = [  1,    2]          // weights: x0 = 1, x1 = 2

model.addSOS sosv1, soswt1, GRB.SOS_TYPE1
```

:bulb: **Note:** In Groovy, parentheses in a function call are optional,
and may be omitted to improve readability.

## Error Handling

The Groovy version omits explicit error handling, because any errors
would be appropriately reported by Groovy error reporting. This includes errors
from the Gurobi API, such as `GRBException`.

For example, if we omit a SOS weight item in the list `soswt1`, we get an error
indicating the source line `sos.groovy:21`.

```
Caught: gurobi.GRBException: Invalid arguments
gurobi.GRBException: Invalid arguments
        at gurobi.GRBModel.addSOS(GRBModel.java:2893)
        at gurobi.GRBModel$addSOS$0.call(Unknown Source)
        at sos.run(sos.groovy:21)
```

In production scenario, Groovy error handling would be similar to Java.
