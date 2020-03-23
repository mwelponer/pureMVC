package application.controller;

import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import application.ApplicationFacade;
import application.model.ItemProxy;
import application.view.MainWindowMediator;

public class StartupCommand extends SimpleCommand implements ICommand {
	
	@Override
	public final void execute(INotification notification) {
		
		System.out.println("StartupCommand: register ItemProxy and MainWindowMediator");
		getFacade().registerProxy(new ItemProxy());
		getFacade().registerMediator(new MainWindowMediator());
		
		System.out.println("StartupCommand: send notification LOAD_ITEMS");
		getFacade().sendNotification(ApplicationFacade.LOAD_ITEMS);
		
		System.out.println("StartupCommand: send notification SHOW_MAIN_WINDOW");
        // Show the main window
        getFacade().sendNotification(ApplicationFacade.SHOW_MAIN_WINDOW);

        // Remove the command because it never be called more than once
        getFacade().removeCommand(ApplicationFacade.STARTUP);
	}
}
