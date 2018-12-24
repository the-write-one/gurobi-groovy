This example formulates and solves the following simple MIP model:
```
   maximize
         x +   y + 2 z
   subject to
         x + 2 y + 3 z <= 4
         x +   y       >= 1
   x, y, z binary
```

* View source: [mip1.groovy](mip1.groovy)
* Compare: [mip1.py](https://www.gurobi.com/documentation/8.1/examples/mip1_py.html),
  [Mip1.java](https://www.gurobi.com/documentation/8.1/examples/mip1_java.html)
