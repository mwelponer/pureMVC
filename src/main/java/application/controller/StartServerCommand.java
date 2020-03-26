package application.controller;

import application.ApplicationFacade;
import application.model.server.ServerPreferencesProxy;
import application.model.server.ServerProxy;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class StartServerCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(INotification notification) {
        System.out.println("  StartServerCommand: execute()");

        ServerPreferencesProxy serverPreferencesProxy = (ServerPreferencesProxy)notification.getBody();

        ServerProxy server = new ServerProxy(serverPreferencesProxy.getServerPrefs());
        getFacade().registerProxy(server);

        new Thread(server).start();
        sendNotification(ApplicationFacade.SERVER_STARTED);
    }
}
