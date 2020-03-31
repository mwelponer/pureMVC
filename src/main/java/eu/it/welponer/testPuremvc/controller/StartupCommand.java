package eu.it.welponer.testPuremvc.controller;

import eu.it.welponer.testPuremvc.model.client.ClientProxy;
import eu.it.welponer.testPuremvc.model.messages.MessageProxy;
import eu.it.welponer.testPuremvc.model.server.ServerPreferencesProxy;
import eu.it.welponer.testPuremvc.ApplicationFacade;
import eu.it.welponer.testPuremvc.view.MainWindowMediator;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class StartupCommand extends SimpleCommand implements ICommand {
	
	@Override
	public final void execute(INotification notification) {
		System.out.println("  StartupCommand: execute");

		///////////////////////
		// models (proxies)
		getFacade().registerProxy(new MessageProxy());
		getFacade().registerProxy(new ClientProxy());
		getFacade().registerProxy(new ServerPreferencesProxy());

		///////////////////////
		// gui (mediators)
		getFacade().registerMediator(new MainWindowMediator());
        getFacade().sendNotification(ApplicationFacade.SHOW_MAIN_WINDOW);

		///////////////////////
        // server
		// load the config for the server (port, localhost) from a config file
		ServerPreferencesProxy serverPreferencesProxy =
				(ServerPreferencesProxy) getFacade().retrieveProxy(ServerPreferencesProxy.NAME);
        getFacade().sendNotification(ApplicationFacade.START_SERVER, serverPreferencesProxy);

        // Remove the command because it never be called more than once
        getFacade().removeCommand(ApplicationFacade.STARTUP);
	}
}
