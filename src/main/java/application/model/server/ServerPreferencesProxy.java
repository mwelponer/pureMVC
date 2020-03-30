package application.model.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.puremvc.java.multicore.interfaces.IProxy;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServerPreferencesProxy extends Proxy implements IProxy {

    public static final String NAME = "serverPreferencesProxy";
    public static final int INITIAL_SERVER_PORT = 9000;

    // Preference file
    private File prefFile = new File(System.getProperty("user.home"), ".geobly_srv_prefs");

    // Gson serializer
    private Gson gson;

    @Setter @Getter
    private ServerPreferencesVO serverPrefs;

    public ServerPreferencesProxy() {
        super(NAME);
        System.out.println("ServerPreferencesProxy()");

        // Initialize gson
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        gson = builder.create();

        // Load preferences from file in user.home
        System.out.println("  ..load server preferences from file");
        if( prefFile.exists() ) {
            try(FileReader reader = new FileReader(prefFile)) {
                serverPrefs = gson.fromJson(reader, ServerPreferencesVO.class);
                System.out.println("  ..serverPort " + serverPrefs.getServerPort());
            } catch (IOException e) {
                e.printStackTrace();
                createNewFile();
            }
        }
        else {
            createNewFile();
        }
    }

    private void createNewFile(){
        System.out.println("  ServerPreferencesProxy: createNewFile()");
        serverPrefs = new ServerPreferencesVO();
//        System.out.println("ServerPreferencesProxy: set server port " + SERVER_PORT);
        // user ports 1024-49151
        this.setPort(INITIAL_SERVER_PORT);
//        System.out.println("ServerPreferencesProxy: save server preferences");
        savePreferences();
    }

    public void setPort(int port){
        serverPrefs.setServerPort(port);
    }

    public void savePreferences(){
        System.out.println("  ServerPreferencesProxy: savePreferences()");
        try(FileWriter writer = new FileWriter(prefFile)) {
            gson.toJson(serverPrefs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
