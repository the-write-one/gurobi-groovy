# Gurobi Models in Groovy

**Gurobi Optimizer models defined and executed in Groovy environment**

Groovy is a powerful scripting language based on Java.
The Groovy language and it's programming environment can be used on its own by
organizations and developers working on the Java platform.
Yet most importantly, be it desktops, web servers, or mobile devices, Groovy 
is especially useful because of extensibility and customization features 
provided for a Java-based product.

## Groovy Features

Groovy provides the following features making it a premier candidate for interactive analytical computation:

* a **scripting language**, which does not require build step and can be executed interactively,
  similar to Python, MATLAB, the R environment
* a **light-weight layer on top of Java**, which takes advantage of large variety of standard and third-party libraries
* a **flexible syntax** conducive to creating a [DSL](http://docs.groovy-lang.org/docs/latest/html/documentation/core-domain-specific-languages.html) (domain-specific language); the benefits including optional parentheses,
  pipe-line (command chain) notation, operator overloading, etc
* an **easy custom engine API**, which allows restricting and sandboxing syntax and execution in 
  security sensitive environments, such as web and enterprise applications, while allowing user extensibility at run-time
* **functional programming** aspects, produce compact intuitive definitions close to mathematical notation;
  examples including list comprehensions, collection abstractions, types inferrence

## Adapting Gurobi Examples

Here we illustrate how a **[Groovy environment](bin/bin.md)** can be configured alongside Gurobi installation.
A number of familiar Gurobi examples will show several useful features of Groovy as Gurobi programming interface.

### Groovy Environment

These examples show integration of Groovy environment and Gurobi installation.
Examples of using command-line execution of Groovy scripts and Groovy Console are provided.

* [sos](sos/sos.md) - a very simple Special Ordered Set (SOS) model

* [mip2](mip2/mip2.md) - reads a MIP model from a file

### Integrating Gurobi API in Groovy

These examples show how to use algebraic notation by overloading Groovy operators.

* [mip1](mip1/mip1.md) - formulates and solves a simple MIP model

* [piecewise](piecewise/piecewise.md) - considers a separable, convex problem, defined by a functional goal

