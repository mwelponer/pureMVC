package eu.it.welponer.testPuremvc.controller.server;

import eu.it.welponer.testPuremvc.ApplicationFacade;
import eu.it.welponer.testPuremvc.model.server.ServerPreferencesProxy;
import eu.it.welponer.testPuremvc.model.server.ServerProxy;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class StartServerCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(INotification notification) {
        System.out.println("  StartServerCommand: execute()");

        ServerPreferencesProxy serverPreferencesProxy = (ServerPreferencesProxy)notification.getBody();

        ServerProxy serverProxy = new ServerProxy(serverPreferencesProxy.getServerPrefs());
        getFacade().registerProxy(serverProxy);

        new Thread(serverProxy).start();
        sendNotification(ApplicationFacade.SERVER_STARTED, serverPreferencesProxy.getServerPrefs().getServerPort());

        sendNotification(ApplicationFacade.UPDATE_STATUSBAR, "server listening on port "
                + serverPreferencesProxy.getServerPrefs().getServerPort());
    }
}
