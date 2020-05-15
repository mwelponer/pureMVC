package eu.it.welponer.testPuremvc.controller;

import eu.it.welponer.testPuremvc.Main;
import eu.it.welponer.testPuremvc.model.server.ServerPreferencesProxy;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class SetDebuglevelCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(final INotification notification) {
        if(Main.debugLevel > 1)
            System.out.println("  SetDebuglevelCommand: execute()");

        Main.debugLevel = (int)notification.getBody();

        //ToDo: update Main.debugLevel variable & update preferences file too
        ServerPreferencesProxy preferencesProxy =
                (ServerPreferencesProxy) getFacade().retrieveProxy(ServerPreferencesProxy.NAME);

        preferencesProxy.setDebugLevel((int)notification.getBody());
    }
}
