Puremvc Framework RESTc/s
==================

## What is it
The tool acts as a server listening for incoming (http REST) TCP messages on a
specific port, but it can also work as a client to send json messages
via TCP to other PCs connected to the network.
Copyright (C) 2020  Michele Welponer, mwelponer@gmail.com (Fondazione Bruno Kessler)

This program comes with ABSOLUTELY NO WARRANTY;
This is free software, and you are welcome to redistribute it
under certain conditions;


### Using the tool as a server
The very first time it runs the server listens on port 9000. You can change it
 using menu Edit > Change server port.
Inside the second textbox (the console) you will see the incoming json messages.

You can use a free software like [Postman](https://www.postman.com/downloads/) to
send messages to the computer running the tool. After downloading Postman use the following settings inside Postman

- create a request: select POST, request url: theIpOfThePcRunningTestPuremvc:9000
- in the body tab select raw and write the json message
e.g. {"coordX": 0.111,"coordY": 6.666}
e.g. {"coords":[31117.4, 233488.7], "debugLevel": 0}
- in the last body tab Text change it to JSON
- send

You can also use the tool in client mode (see below) to send messages to another PC connected to the network where an instance of the same tool is acting as a server.

### Using the tool as a client
Let's say you are PCa (192.168.1.1) and you want to send a json message to
PCb (192.168.2.2) that is already running the tool listening on port 9000.

- Edit the first textbox with the json message you want to send, by default is 
{"coords":[31117.4, 233488.7], "debugLevel": 0} but you can type whatever json file you want.
If the json contains both the objects 'coordX' and 'coordY' or the object 'coords', the server will
recognise them as coordinates.
- In the bottom part of the tool, select POST or GET, insert the destination
(in our case http://192.168.2.2:9000) and hit Send. In the console, you will see
delivered messages receipts

This README would normally document whatever steps are necessary to get your
application up and running.

