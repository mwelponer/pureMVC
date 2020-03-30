package application.model.server;

import application.ApplicationFacade;
import application.model.messages.MessageVO;
import org.json.JSONObject;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

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
            if(stringBuffer.toString().isEmpty()) {clientSocket.close(); return;}
            else trimmedStringBuffer = stringBuffer.toString().trim();

            //TODO: send update output console to facade
            //ApplicationFacade.getInstance().sendNotification(ApplicationFacade.UPDATE_CONSOLE, stringBuffer.toString());
            System.out.println("  --- HEADER ---\n" + stringBuffer.toString());


            /////////////////////////////////////////////
            // READ HTTP PAYLOAD
            //code to read the post payload data
            JSONObject jsonObject = null;
            if(trimmedStringBuffer.startsWith("GET")){
                // percent decript chars
                String decPayload = trimmedStringBuffer.replace("%22", "\""); // %22 -> "
                decPayload = decPayload.replace("%7B", "{"); // %7B -> {
                decPayload = decPayload.replace("%7D", "}"); // %7D -> }
                decPayload = decPayload.replace("%5B", "["); // %5B -> [
                decPayload = decPayload.replace("%5D", "]"); // %5D -> ]

                System.out.println("decPayload: '" + decPayload + "'");

                // remove all that is not json
                if(decPayload.contains("{") && decPayload.contains("}")) {
                    decPayload = decPayload.substring(decPayload.indexOf('{') + 1);
                    decPayload = decPayload.substring(0, decPayload.indexOf('}'));
                    //add curly brackets again
                    decPayload = "{" + decPayload + "}";
                    jsonObject = new JSONObject(decPayload);
                }

            }else if (trimmedStringBuffer.startsWith("POST")){
                StringBuilder payload = new StringBuilder();

                while (inBufferReader.ready()) {
                    payload.append((char) inBufferReader.read());
                }

                System.out.println("  --- PAYLOAD ---\n  '" + payload + "'");

                if(!payload.toString().isEmpty())
                    jsonObject = new JSONObject(payload.toString());
            }


            /////////////////////////////////////////////
            // WRITE BACK TO CLIENT
            OutputStream outStream = clientSocket.getOutputStream();
            //String ContentLength = "Content-Length:" + bufferedReader.readLine().length() + "\r\n";
            try {
                // Header should be ended with '\r\n' at each line
//                if(jsonObject == null)
//                    outStream.write("HTTP/1.1 204 No Content\r\n".getBytes());
//                else
                outStream.write("HTTP/1.1 200 OK\r\n".getBytes());

                //outStream.write("Main: OneServer 0.1\r\n".getBytes());
                outStream.write("Content-Length: 22\r\n".getBytes()); // if text/plain the length is required
                //outStream.write(ContentLength.getBytes()); // if text/plain the length is required
                outStream.write("Content-Type: text/plain\r\n".getBytes());
                //outStream.write("Connection: close\r\n".getBytes());

                // An empty line is required after the header
                outStream.write("\r\n".getBytes());

                if(trimmedStringBuffer.startsWith("POST")) {
                    // insert response in payload
                    if(jsonObject == null)
                        outStream.write("HTTP/1.1 204 No Content\r\n".getBytes());
                    else
                        outStream.write("HTTP/1.1 200 OK\r\n".getBytes());

                    outStream.write("Content-Type: text/plain\r\n".getBytes());
                    outStream.write("\r\n".getBytes());
                }

                outStream.flush();
                outStream.close(); // Socket will close automatically once output stream is closed.
            } catch (SocketException e) {
                // Handle the case where client closed the connection while server was writing to it
                clientSocket.close();
            }


            /////////////////////////////////////////////
            // JSON
            //System.out.println("payload: " + payload.toString());
            if(jsonObject != null) {
                //TODO: use receiveMessageCommand
                MessageVO message = new MessageVO();
                message.setTimestamp(System.currentTimeMillis());
                message.setJsonObject(jsonObject);
                ApplicationFacade.getInstance().sendNotification(ApplicationFacade.RECEIVE_MESSAGE, message);
                //ApplicationFacade.getInstance().sendNotification(ApplicationFacade.ADD_ITEM, new ItemVO("ciccio"));
                //System.out.println(jsonObject);

                System.out.println("  ..message processed");
            }
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
