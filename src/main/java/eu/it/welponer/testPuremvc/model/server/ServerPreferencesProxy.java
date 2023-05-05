/*
 * Copyright (C) 2020  Michele Welponer, mwelponer@gmail.com (Fondazione Bruno Kessler)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/> and file GPL3.txt
 */

package eu.it.welponer.testPuremvc.model.server;

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
    private File prefFile = new File(System.getProperty("user.home"), ".testPuremvc_srv_prefs");

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
    public void setDebugLevel(int level) { serverPrefs.setDebugLevel(level); }

    public void savePreferences(){
        System.out.println("  ServerPreferencesProxy: savePreferences()");
        try(FileWriter writer = new FileWriter(prefFile)) {
            gson.toJson(serverPrefs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
