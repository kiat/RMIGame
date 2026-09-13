# A Simple Game using Java Remote Method Invocation (RMI)
    


# Project Template

This is a Java Maven Project Template


# How to compile the project

We use Apache Maven to compile and run this project. 

You need to install Apache Maven (https://maven.apache.org/)  on your system. 

Type on the command line: 

```bash
mvn clean compile
```

# How to create a binary runnable package 


```bash
mvn clean compile assembly:single
```


# How to run

```bash
mvn clean compile  exec:java -Dexec.args="WikipediaPagesOneDocPerLine.txt.bz2 300"
```


```bash
mvn clean compile  exec:java -Dexec.executable="edu.utexas.cs.cs378.Main"  -Dexec.args="WikipediaPagesOneDocPerLine.txt.bz2 500"
```
