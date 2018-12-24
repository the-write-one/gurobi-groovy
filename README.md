# Gurobi Models in Groovy

** Gurobi Optimizer models definied and executed in Groovy environment**

Groovy is a powerful scripting language and environment based on Java.
It can be used stand-alone by organizations and developers using Java platform.
But it is especially useful as an extensibility and customization feature,
integrated into Java-based applications, be it desktop, web servers or mobile.

## Groovy Features

* **Scripting language**, which does not require complation infrastructure, can be executed interactively; 
  similar to Python, matlab, R
* **Light-weight layer on top of Java**, taking advantage of large variety of standard and third-party libraries
* **Flexible syntax** codusive to creating a DSL, benefitting from optional parentheses, 
  allowing pipe-line (command chain) notation, operator overloading
* **Easy custom engine API**, which allows restricting and sandboxing syntax and execution in 
  security sensitive environments, such as web and enterprose applications, which allowing user extensibility at run-time
* **Functional Programming** aspects produce compact intuitive definitions close to mathematical notation.
  Examples include list comprehensions, collection abstractions, types inferrence

## Adapting Gurobi Examples

Here we will illustrate how a Groovy environment can be configured alongside Gurobi installation.
A number of familiar Gurobi examples will show several useful features of Groovy as Gurobi programming interface.

### Groovy Environment

These examples will show integration of Groovy environment and Gurobi installation.
Examples of using command-line execution of Groovy scripts and Groovy Console are provided.

* [sos](sos/sos.md) - a very simple Special Ordered Set (SOS) model

* [mip2](mip2/mip2.md) - reads a MIP model from a file

### Integrating Gurobi API in Groovy

These example will show how to use algebraic notation by overloading Groovy operators.

* [mip1](mip1/mip1.md) - formulates and solves a simple MIP model

* [piecewise](piecewise/piecewise.md) - considers a separable, convex problem, defined by a functional goal

