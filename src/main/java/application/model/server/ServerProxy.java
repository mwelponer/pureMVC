package application.model.server;

import application.ApplicationFacade;
import application.model.messages.MessageVO;
import org.puremvc.java.multicore.interfaces.IProxy;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Date;

public class ServerProxy extends Proxy implements IProxy, Runnable {

    public static final String NAME = "ServerProxy";

    protected boolean isStopped = false;
    protected int serverPort;
    protected ServerSocket serverSocket;
    protected Thread runningThread;

    public ServerProxy(ServerPreferencesVO prefs) {
        super(NAME, prefs);
        this.serverPort = prefs.getServerPort();
        System.out.println("ServerProxy()");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        System.out.println("  ServerProxy: stop()");
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(" ..error closing server", e);
        }
    }

    private void openServerSocket() {
        System.out.println("  ServerProxy: openServerSocket()");
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("  ..cannot open server socket on port " + serverPort, e);
        }
    }

    @Override
    public void run() {
        System.out.println("  ServerProxy: run()");

        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        System.out.println("  ..server running on port " + this.serverPort);
        while(! isStopped()){
            //System.out.print(".");
            Socket clientSocket;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("  ..server Stopped.");
                    return;
                }
                throw new RuntimeException("  ..error accepting client connection", e);
            }
            long time = System.currentTimeMillis();
            Date resultdate = new Date(time);
            System.out.println("  ..message received: " + resultdate.toString());

            new Thread(new MessageProcessor(clientSocket, "Multithreaded Server")
            ).start();
        }

        System.out.println("  ..server Stopped.");
    }

    public final String sendRequest(MessageVO message) {
        RequestMethod method = message.getMethod();
        String targetURL = message.getTargetURL();
        String payload = message.getJsonObject().toString();

        // targetURL -> "http://localhost:9000"
        // payload -> "{\"timestamp\":\"1234\", \"coordX\":\"1.0\", \"coordY\":\"1.0\"}"

        URL url;
        HttpURLConnection connection = null;
        String charset = "UTF-8";
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod(method.name());
            //connection.setDoOutput(true); // Triggers POST.

            //connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);

            connection.setRequestProperty("Content-Length", "" +
                    Integer.toString(payload.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
            wr.writeBytes (payload);
            wr.flush ();
            wr.close ();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }

            //TODO: send response to the outputconsole
            ApplicationFacade.getInstance().sendNotification(
                    ApplicationFacade.UPDATE_CONSOLE, response.toString());

            rd.close();
            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }

//    public static void main(String[]args){
//        ServerPreferencesProxy prefs = new ServerPreferencesProxy();
//        prefs.setPort(9000);
//
//        ServerProxy server = new ServerProxy(prefs.getServerPrefs());
//        new Thread(server).start();
//
//        try {
//            Thread.sleep(20 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Stopping Server");
//        server.stop();
//    }
}
