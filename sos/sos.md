This example creates a very simple Special Ordered Set (SOS) model.
The model consists of 3 continuous variables, no linear constraints,
and a pair of SOS constraints of type 1.

* View source: [sos.groovy](https://github.com/the-write-one/gurobi-groovy/blob/master/sos/sos.groovy)
* Compare: [sos.py](https://www.gurobi.com/documentation/8.1/examples/sos_py.html),
           [Sos.java](https://www.gurobi.com/documentation/8.1/examples/sos_java.html)



:bulb: **Note:** in the Groovy version omits error handling, because any errors
would be appropriately reported by Groovy error-handling. This includes error
from the Gurobi API, such as `GRBException`.
