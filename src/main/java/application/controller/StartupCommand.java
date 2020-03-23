package application.controller;

import application.model.server.ServerPreferencesProxy;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import application.ApplicationFacade;
import application.model.items.ItemProxy;
import application.view.MainWindowMediator;

public class StartupCommand extends SimpleCommand implements ICommand {
	
	@Override
	public final void execute(INotification notification) {

		// models (proxies)
		System.out.println("StartupCommand: register ItemProxy and ServerPreferencesProxy");
		getFacade().registerProxy(new ItemProxy());
		getFacade().registerProxy(new ServerPreferencesProxy());

		// gui (mediators)
		getFacade().registerMediator(new MainWindowMediator());
		System.out.println("StartupCommand: send notification LOAD_ITEMS");
		getFacade().sendNotification(ApplicationFacade.LOAD_ITEMS);
		System.out.println("StartupCommand: send notification SHOW_MAIN_WINDOW");
        getFacade().sendNotification(ApplicationFacade.SHOW_MAIN_WINDOW);

        // server
		System.out.println("StartupCommand: send notification START_SERVER");
        getFacade().sendNotification(ApplicationFacade.START_SERVER);

        // Remove the command because it never be called more than once
        getFacade().removeCommand(ApplicationFacade.STARTUP);
	}
}
