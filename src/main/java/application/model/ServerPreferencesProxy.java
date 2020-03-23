package application.model;

import application.model.vo.ServerPreferencesVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.puremvc.java.multicore.interfaces.IProxy;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServerPreferencesProxy extends Proxy implements IProxy {

    public static final String NAME = "serverPreferencesProxy";

    // Preference file
    private File prefFile = new File(System.getProperty("user.home"), ".geobly_srv_prefs");

    // Gson serializer
    private Gson gson;

    private ServerPreferencesVO serverPrefs;

    public ServerPreferencesProxy(String proxyName) {
        super(NAME);

        // Initialize gson
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        gson = builder.create();

        // Load preferences from file in user.home
        if( prefFile.exists() ) {
            try(FileReader reader = new FileReader(prefFile)) {
                serverPrefs = gson.fromJson(reader, ServerPreferencesVO.class);
            } catch (IOException e) {
                e.printStackTrace();
                serverPrefs = new ServerPreferencesVO();
                savePreferences();
            }
        }
        else {
            serverPrefs = new ServerPreferencesVO();
            savePreferences();
        }
    }

    public void setPort(int port){
        serverPrefs.setServerport(port);
    }

    private void savePreferences(){
        try(FileWriter writer = new FileWriter(prefFile)) {
            gson.toJson(serverPrefs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
