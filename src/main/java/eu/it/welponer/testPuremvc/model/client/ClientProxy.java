package eu.it.welponer.testPuremvc.model.client;

import eu.it.welponer.testPuremvc.model.messages.MessageVO;
import eu.it.welponer.testPuremvc.model.server.ServerProxy;
import eu.it.welponer.testPuremvc.view.MainWindowMediator;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.puremvc.java.multicore.interfaces.IProxy;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientProxy extends Proxy implements IProxy {
    public static final String NAME = "ClientProxy";

    private final String USER_AGENT = "TestPureMVC/1.0";
    public static final String CHARSET = "UTF-8";

    private HttpURLConnection connection;

    // one instance, reuse
    private final CloseableHttpClient httpClient;

    public ClientProxy() {
        super(NAME);
        System.out.println("ClientProxy()");

        httpClient = HttpClients.createDefault();
    }

    public final String sendMessage(MessageVO message) {
        System.out.println("  ClientProxy: sendMessage()");

        RequestMethod method = message.getMethod();
        System.out.println("  ..method: " + method.name());
        String targetURL = message.getTargetURL();
        String payload = message.getJsonObject().toString();

        String response = null;
        try{
            switch(message.getMethod()){
                case GET:
                    response = sendGet(targetURL, payload);
                    break;
                case POST:
                    response = sendPost(targetURL, payload);
                    break;
                default:
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }

//        // unlock writing to console
//        MainWindowMediator mainWindowMediator = (MainWindowMediator) getFacade().retrieveMediator(MainWindowMediator.NAME);
//        mainWindowMediator.setLock(false);

        return response;
    }

    // HTTP POST request
    private String sendPost(String targetURL, String payload) throws Exception {
        System.out.println("  ClientProxy: sendPost()");

        long time = System.currentTimeMillis();
        Date resultdate = new Date(time);

//        String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ssZ";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
//        String dateString = simpleDateFormat.format(new Date());

        //Create connection
        URL url = new URL(targetURL);
        connection = (HttpURLConnection)url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept-Charset", CHARSET);
        connection.setRequestProperty("Content-Type", "eu/it/welponer/testPuremvc/json;charset=" + CHARSET);
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Content-Language", "en-US");
        connection.setRequestProperty("Content-Length", "" + payload.getBytes().length);

        connection.setUseCaches (false);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        StringBuffer response = new StringBuffer();

        //Send request
        try {
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            System.out.println("  ..payload: " + payload);
            wr.writeBytes(payload);
            wr.flush();
            wr.close();

            //Get Response from the server (read response into payload)
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line, statusCode, contentType;
            List<String>responseL = new ArrayList<>();

            while((line = rd.readLine()) != null) {
//            response.append(line);
//            response.append('\r');
                responseL.add(line);
            }

            System.out.println("  --- HEADER ---\n  " + responseL.get(0) + "\n  " + responseL.get(1));

            //TODO: send response to the outputconsole
            response.append(resultdate + " - server reply: " + responseL.get(0));
//        ApplicationFacade.getInstance().sendNotification(
//                ApplicationFacade.UPDATE_CONSOLE, resultdate + " - server reply: HTTP/1.1 200 OK");


            rd.close();

        }catch(ConnectException e){
            JOptionPane.showMessageDialog(null,
                    "Connection refused. Verify the server is up and running.",
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }catch(UnknownHostException e){
            JOptionPane.showMessageDialog(null,
                    "Unknown host. Verify the server url you are trying to connect to is correct.",
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }

        return response.toString();
    }

    // HTTP GET request
    private String sendGet(String targetURL, String payload) throws Exception {
        System.out.println("  ClientProxy: sendGet()");

        long time = System.currentTimeMillis();
        Date resultdate = new Date(time);

        // % encripts special chars
        // http://localhost:9000?%7BcoordX=0.9&coordY=0.1%7D
        String encPayload = payload.replace("\"", "%22"); // " -> %22
        encPayload = encPayload.replace("{", "%7B"); // { -> %7B
        encPayload = encPayload.replace("}", "%7D"); // } -> %7D
        encPayload = encPayload.replace("[", "%5B"); // [ -> %5B
        encPayload = encPayload.replace("]", "%5D"); // ] -> %5D

        StringBuilder stringBuilder = new StringBuilder(targetURL.replace(" ", ""));
        stringBuilder.append("?json=");
        stringBuilder.append(encPayload);

        HttpGet request = new HttpGet(stringBuilder.toString());

        // add request headers
        request.addHeader(HttpHeaders.USER_AGENT, USER_AGENT);

        String response = null;
        try (CloseableHttpResponse httpResponse = httpClient.execute(request)) {
            // get response from the hhtpClient
            HttpEntity entity = httpResponse.getEntity();
            Header headers = entity.getContentType();

            time = System.currentTimeMillis();
            resultdate = new Date(time);
            response = resultdate + " - server reply: " + httpResponse.getStatusLine().toString();

            System.out.println("  --- HEADER ---\n  " +
                    httpResponse.getStatusLine().toString() + "\n  " + entity.getContentType() + "\n");
            //ApplicationFacade.getInstance().sendNotification(ApplicationFacade.UPDATE_CONSOLE, response);

//            if (entity != null) {
//                // return it as a String
//                String result = EntityUtils.toString(entity);
//                System.out.println(result);
//            }
        }catch(HttpHostConnectException e){
            JOptionPane.showMessageDialog(null,
                    "Connection refused. Verify the server is up and running.",
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }catch(UnknownHostException e){
            JOptionPane.showMessageDialog(null,
                    "Unknown host. Verify the server url you are trying to connect to is correct.",
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }

        return response;
    }
}
