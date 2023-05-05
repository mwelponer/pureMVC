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

import lombok.Getter;
import lombok.Setter;
import org.puremvc.java.multicore.interfaces.IProxy;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerProxy extends Proxy implements IProxy, Runnable {

    public static final String NAME = "ServerProxy";

    protected boolean isStopped = false;
    protected int serverPort;
    protected ServerSocket serverSocket;

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
            if(this.serverSocket != null)
                this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(" ..error closing server", e);
        }
    }

    private boolean openServerSocket() {
        System.out.println("  ServerProxy: openServerSocket()");
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Cannot open server socket on port " + this.serverPort,
                    "InfoBox: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("  ..cannot open server socket on port " + serverPort, e);
        }
    }

    @Override
    public void run() {
        System.out.println("  ServerProxy: run()");

        if(openServerSocket()) {
            System.out.println("  ..server running on port " + this.serverPort);
            while (!isStopped()) {
                //System.out.print(".");
                Socket clientSocket;
                try {
                    clientSocket = this.serverSocket.accept();
                } catch (IOException e) {
                    if (isStopped()) {
                        System.out.println("  ..server Stopped.");
                        return;
                    }
                    throw new RuntimeException("  ..error accepting client connection", e);
                }
                long time = System.currentTimeMillis();
                Date resultdate = new Date(time);

//            String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ssZ";
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
//            String dateString = simpleDateFormat.format(new Date());
                System.out.println("  ..message received: " + resultdate);

                new Thread(new MessageProcessor(clientSocket, "Multithreaded Server")
                ).start();
            }

            System.out.println("  ..server Stopped.");
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
