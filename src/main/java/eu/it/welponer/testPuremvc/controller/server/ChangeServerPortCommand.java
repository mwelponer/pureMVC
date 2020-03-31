package eu.it.welponer.testPuremvc.controller.server;

import eu.it.welponer.testPuremvc.model.server.ServerPreferencesProxy;
import eu.it.welponer.testPuremvc.model.server.ServerProxy;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class ChangeServerPortCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(INotification notification) {
        int port = (Integer)notification.getBody();

        // stop server
        ServerProxy serverProxy = (ServerProxy)getFacade().retrieveProxy(ServerProxy.NAME);
        serverProxy.stop();
        getFacade().removeProxy(ServerProxy.NAME);

        // change ServerPreferencesProxy
        ServerPreferencesProxy serverPreferencesProxy =
                (ServerPreferencesProxy)getFacade().retrieveProxy(ServerPreferencesProxy.NAME);

        System.out.println(serverPreferencesProxy == null ? "serverPreferencesProxy is null" : "serverPreferencesProxy not null");
        serverPreferencesProxy.setPort(port);
        serverPreferencesProxy.savePreferences();

        // start server again
        serverProxy = new ServerProxy(serverPreferencesProxy.getServerPrefs());
        getFacade().registerProxy(serverProxy);

        new Thread(serverProxy).start();
        //sendNotification(ApplicationFacade.SERVER_STARTED, port);
    }
}
