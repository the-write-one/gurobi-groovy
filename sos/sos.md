This example creates a very simple Special Ordered Set (SOS) model.
The model consists of 3 continuous variables, no linear constraints,
and a pair of SOS constraints of type 1.

* View source: [sos.groovy](https://github.com/the-write-one/gurobi-groovy/blob/master/sos/sos.groovy)
* Compare: [sos.py](https://www.gurobi.com/documentation/8.1/examples/sos_py.html),
           [Sos.java](https://www.gurobi.com/documentation/8.1/examples/sos_java.html)


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
