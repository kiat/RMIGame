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


# How to run the Server

```bash
mvn clean  compile  exec:java@server -Dexec.args="127.0.0.1  1099"
```

# How to run the Client

```bash
mvn clean  compile  exec:java@client  -Dexec.args="127.0.0.1 1099"
```

Change the localhost with the local ip address of your cloud machine. 
