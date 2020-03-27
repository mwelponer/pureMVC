package application.model.server;

import application.ApplicationFacade;
import application.model.messages.MessageVO;
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
            long time = System.currentTimeMillis();
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

            String trimmedStringBuffer;
            if(stringBuffer.toString().isEmpty()) return;
            else trimmedStringBuffer = stringBuffer.toString().trim();

            //TODO: send update output console to facade
            //ApplicationFacade.getInstance().sendNotification(ApplicationFacade.UPDATE_CONSOLE, stringBuffer.toString());
            System.out.println("  --- HEADER ---\n" + stringBuffer.toString());


            time = System.currentTimeMillis();
            resultdate = new Date(time);
            OutputStream outStream = null;
            BufferedReader bufferedReader = null;

            /////////////////////////////////////////////
            // WRITE BACK TO CLIENT
            if(trimmedStringBuffer.startsWith("POST")) {
                outStream = clientSocket.getOutputStream();
                bufferedReader = new BufferedReader(
                        new StringReader(resultdate + " - server reply: HTTP/1.1 200 OK"));
            }

            //String ContentLength = "Content-Length:" + bufferedReader.readLine().length() + "\r\n";

            try {
                System.out.println("passssssssssssssso!!!");
                // Header should be ended with '\r\n' at each line
                outStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                //outStream.write("Main: OneServer 0.1\r\n".getBytes());
                outStream.write("Content-Length: 22\r\n".getBytes()); // if text/plain the length is required
                //outStream.write(ContentLength.getBytes()); // if text/plain the length is required
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
            JSONObject jsonObject = null;
            if(trimmedStringBuffer.startsWith("GET")){
                // percent decript chars
                String decPayload = trimmedStringBuffer.replace("%22", "\"");
                decPayload = decPayload.replace("%7B", "{");
                decPayload = decPayload.replace("%7D", "}");
                // remove all that is not json
                decPayload = decPayload.substring(decPayload.indexOf('{')+1);
                decPayload = decPayload.substring(0, decPayload.indexOf('}'));
                //add curly brackets again
                decPayload = "{" + decPayload + "}";
                jsonObject = new JSONObject(decPayload);
            }else {
                StringBuilder payload = new StringBuilder();
                while (inBufferReader.ready()) {
                    payload.append((char) inBufferReader.read());
                }
                jsonObject = new JSONObject(payload.toString());
            }
            //System.out.println("Payload data is: " + payload.toString());

            // JSON
            //System.out.println("payload: " + payload.toString());


            //TODO: use receiveMessageCommand
            MessageVO message = new MessageVO();
            message.setTimestamp(time);
            message.setJsonObject(jsonObject);
            ApplicationFacade.getInstance().sendNotification(ApplicationFacade.RECEIVE_MESSAGE, message);
            //ApplicationFacade.getInstance().sendNotification(ApplicationFacade.ADD_ITEM, new ItemVO("ciccio"));
            //System.out.println(jsonObject);

//            if(!jsonObject.has("id"))
//                System.out.println("  ..not found");
//            else
//                System.out.println("  id: " + jsonObject.getInt("id"));


            System.out.println("  ..message processed: " + resultdate);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
