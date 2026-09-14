# A Simple Game using Java Remote Method Invocation (RMI)

In this activity, you will build a small multiplayer game using Java RMI (Remote Method Invocation).

Your program will have:

* An RMI Server
* One or more RMI Clients
* A shared game running on the server

The client will communicate with the server by calling remote Java methods.

By the end of the activity, you should understand how a Java program can call a method that executes in a different process or on a different computer.


# How to Play 


The server chooses a secret number between 1 and 100.

For example:

Secret number = 73

Students connect to the server and try to guess the number.

A client might send:

Guess: 50

The server responds:

Too low!

The client then sends:

Guess: 85

The server responds:

Too high!

Eventually:

Guess: 73

The server responds:

Correct! You are the winner!

The first student to guess the number wins!


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
