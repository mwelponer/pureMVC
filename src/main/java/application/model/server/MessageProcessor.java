package application.model.server;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageProcessor implements Runnable {

    protected Socket clientSocket;
    protected String serverText;

    public MessageProcessor(Socket clientSocket, String serverText) {
        System.out.println("MessageProcessor()");
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    @Override
    public void run() {
        System.out.println("  MessageProcessor: run()");

        try {
//            InputStream input  = clientSocket.getInputStream();
//            OutputStream output = clientSocket.getOutputStream();
            long time = System.currentTimeMillis();
//
//            output.write(("HTTP/1.1 200 OK\n\nMessageProcessor: " +
//                    this.serverText + " - " +
//                    time +
//                    "").getBytes());
//            output.close();
//
//            input.close();
//
            //SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
            Date resultdate = new Date(time);

            // input stream
            BufferedReader inBufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            StringBuilder stringBuffer = new StringBuilder();

            /////////////////////////////////////////////
            // READ HTTP HEADER
            String inputLine;
            while ((inputLine = inBufferReader.readLine()) != null && !inputLine.equals("")) {
                stringBuffer.append("  " + inputLine);
                stringBuffer.append("\r\n");
            }

            System.out.println(stringBuffer.toString());

            /////////////////////////////////////////////
            // WRITE BACK TO CLIENT
            OutputStream outStream = clientSocket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new StringReader("A Message from server."));

            try {
                // Header should be ended with '\r\n' at each line
                outStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                //outStream.write("Main: OneServer 0.1\r\n".getBytes());
                outStream.write("Content-Length: 22\r\n".getBytes()); // if text/plain the length is required
                outStream.write("Content-Type: text/plain\r\n".getBytes());
                //outStream.write("Connection: close\r\n".getBytes());

                // An empty line is required after the header
                outStream.write("\r\n".getBytes());

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    outStream.write(line.getBytes());
                }

                outStream.flush();
                outStream.close(); // Socket will close automatically once output stream is closed.
            } catch (SocketException e) {
                // Handle the case where client closed the connection while server was writing to it
                clientSocket.close();
            }

            /////////////////////////////////////////////
            // READ HTTP PAYLOAD
            //code to read the post payload data
            StringBuilder payload = new StringBuilder();
            while(inBufferReader.ready()){
                payload.append((char) inBufferReader.read());
            }
            System.out.println("Payload data is: " + payload.toString());

            // JSON
            JSONObject jsonObject = new JSONObject(payload.toString());
            System.out.println(jsonObject);

            if(!jsonObject.has("id"))
                System.out.println("  ..not found");
            else
                System.out.println("  id: " + jsonObject.getInt("id"));


            System.out.println("  ..message processed: " + resultdate);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
