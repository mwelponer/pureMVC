package application.controller;

import application.model.ServerProxy;
import application.model.vo.ServerPreferencesVO;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class StartServerCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(INotification notification) {
        // load the config for the server (port, localhost) from a config file
        ServerPreferencesVO prefs = null; // blabla

        System.out.println("StartupCommand: register ItemProxy and MainWindowMediator");
        getFacade().registerProxy(new ServerProxy(prefs));
    }
}
