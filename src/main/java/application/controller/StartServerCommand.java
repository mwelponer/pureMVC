package application.controller;

import application.model.server.ServerPreferencesProxy;
import application.model.server.ServerProxy;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class StartServerCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(INotification notification) {
        // load the config for the server (port, localhost) from a config file
        System.out.println("StartServerCommand: load server preferences");
        ServerPreferencesProxy serverPreferencesProxy =
                (ServerPreferencesProxy) getFacade().retrieveProxy(ServerPreferencesProxy.NAME);

        System.out.println("StartServerCommand: register ServerProxy");
        ServerProxy server = new ServerProxy(serverPreferencesProxy.getServerPrefs());
        getFacade().registerProxy(server);

        System.out.println("StartServerCommand: starting the server");
        new Thread(server).start();
    }
}
