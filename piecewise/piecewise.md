This example considers the following separable, convex problem:
```
  minimize    f(x) - y + g(z)
  subject to  x + 2 y + 3 z <= 4
              x +   y       >= 1
              x,    y,    z <= 1
```
where `f(u) = exp(-u)` and `g(u) = 2 u^2 - 4 u`, for all real `u`. It
formulates and solves a simpler LP model by approximating `f` and
`g` with piecewise-linear functions. Then it transforms the model
into a MIP by negating the approximation for `f`, which corresponds
to a non-convex piecewise-linear function, and solves it again.

* View source: [piecewise.groovy](https://github.com/the-write-one/gurobi-groovy/blob/master/piecewise/piecewise.groovy)
* Compare: [piecewise.py](https://www.gurobi.com/documentation/8.1/examples/piecewise_py.html),
           [piecewise.java](https://www.gurobi.com/documentation/8.1/examples/piecewise_java.html)
