package application.controller;

import application.model.messages.MessageProxy;
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
		System.out.println("  StartupCommand: execute");

		///////////////////////
		// models (proxies)
//		System.out.println("StartupCommand: register ItemProxy and ServerPreferencesProxy");
		getFacade().registerProxy(new ItemProxy());
		getFacade().registerProxy(new MessageProxy());
		getFacade().registerProxy(new ServerPreferencesProxy());

		///////////////////////
		// gui (mediators)
		getFacade().registerMediator(new MainWindowMediator());
//		System.out.println("StartupCommand: send notification LOAD_ITEMS");
		getFacade().sendNotification(ApplicationFacade.LOAD_ITEMS);
//		System.out.println("StartupCommand: send notification SHOW_MAIN_WINDOW");
        getFacade().sendNotification(ApplicationFacade.SHOW_MAIN_WINDOW);

		///////////////////////
        // server
		// load the config for the server (port, localhost) from a config file
//        System.out.println("StartServerCommand: load server preferences");
		ServerPreferencesProxy serverPreferencesProxy =
				(ServerPreferencesProxy) getFacade().retrieveProxy(ServerPreferencesProxy.NAME);
//		System.out.println("StartupCommand: send notification START_SERVER");
        getFacade().sendNotification(ApplicationFacade.START_SERVER, serverPreferencesProxy);

        // Remove the command because it never be called more than once
        getFacade().removeCommand(ApplicationFacade.STARTUP);
	}
}
