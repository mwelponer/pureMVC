# test_puremvc #

## How to run it:
- windows -> simply run .bat
- linux/osx -> in the terminal type "java -jar testPuremvc-1.0.jar"

## What it it
The tool acts as a server listening for incoming (http rest) TCP messages on a
specific port, but it is also a client, through which you can send json messages
via TCP to other PCs within the same network.

### Using the tool as a server
The very first time it runs the server listens on port 9000. You can change it
though using menu Edit > Change server port.Inside the second textbox (the console)
you will see incoming json messages.

You can use free software like [Postman](https://www.postman.com/downloads/)to
send the message to the computer running the tool. After downloading Postman
just use the following settings inside Postman

- create a request: select POST, request url: theIpOfThePcRunningTestPuremvc:9000
- in the body tab select raw and write the json message
e.g. {"coordX": 0.111,"coordY": 6.666}
- in the last body tab Text change it to JSON
- send

Or you can use the tool itself (better on a second PC), see below

### Using the tool as a client
Let's say you are PCa (192.168.1.1) and you want to send a json message to
PCb (192.168.2.2) that is already running the tool on port 9000.

- Edit the first textbox with the json message you want to send, by default is 
{"coordX": 0.111,"coordY": 6.666} but you can type whatever json file you want.
If the json contains the objects 'coordX' and 'coordY', the server will
recognise them as coordinates.
- In the bottom part of the tool, select POST or GET, insert the destination
(in our case http://192.168.2.2:9000) and hit Send- In the console, you will see
delivered messages receipts

This README would normally document whatever steps are necessary to get your
application up and running.

### What is this repository for? ###

* Quick summary: small project to test inoculation of the server into geobly
* Version 1.0
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)
* [pureMVC](http://www.jkrause.io/blog/2007/12/25/10-tips-for-working-with-puremvc/)
* [singlethreaded server implementation](http://tutorials.jenkov.com/java-multithreaded-servers/singlethreaded-server.html)
* [multithreaded server implementation](http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html)
* [what is JSON](https://stackabuse.com/reading-and-writing-json-in-java/)
* [send JSON over a server](https://danielkvist.net/code/send-a-json-object-to-server-over-tcp-connection-in-java-using-socket)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact
