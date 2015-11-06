# Super Simple Stocks application

## Project description
This exercise project is a simple trading system, this repository does not contain the specification (confidential).

## Requirement:
* Java 1.7
* Maven

## Optional:
* Eclipse with Maven

## How to test?
```sh
mvn test
```
or import project into Eclipse, and run JUnit tests.

### ApplicationTest

The ApplicationTest starts a Trading Simulator in separate thread, which records random trades in every 10 milliseconds.
After one second the Main process calculates and displays stocks values (while simulator is still running).
After it displays a calculation of the GBCE All Share Index.
At the end test process terminates the simulator.

### StockTest

It tests stock value calculations in several steps.

### ExchangeTest

It tests exchange index calculation.
