package application;

import application.controller.StartServerCommand;
import org.puremvc.java.multicore.interfaces.IFacade;
import org.puremvc.java.multicore.patterns.facade.Facade;

import application.controller.AddItemCommand;
import application.controller.StartupCommand;


public class ApplicationFacade extends Facade implements IFacade {

	private static ApplicationFacade instance = new ApplicationFacade();
	public static final String NAME = "ApplicationFacade";
	public static final String STARTUP = "startup";
	public static final String LOAD_ITEMS = "LoadItems";
	public static final String SHOW_MAIN_WINDOW = "show_main_window";
	public static final String SHUTDOWN = "shutdown";
	
	//public static final String ADD_ITEM = "addItem";
	
	public static final String ADD_ITEM = "AddItem";
	public static final String ITEM_ADDED = "ItemAdded";
	public static final String START_SERVER = "StartServer";

	////////////////////////////////////
	// THIRD
	////////////////////////////////////
	protected ApplicationFacade() {
		super(NAME);
		System.out.println("ApplicationFacade: build facade singleton");
	}

	////////////////////////////////////
	// SECOND
	////////////////////////////////////
	public static ApplicationFacade getInstance() {
		return instance;
	}

	////////////////////////////////////
	// FIRST
	////////////////////////////////////
	@Override
	protected void initializeController() {
		// TODO Auto-generated method stub
		super.initializeController();
		System.out.println("ApplicationFacade: register commands");
		registerCommand(STARTUP, new StartupCommand());
		registerCommand(ADD_ITEM, new AddItemCommand());
		registerCommand(START_SERVER, new StartServerCommand());
	}
	
	public void startup() {
		// TODO Auto-generated method stub
		System.out.println("ApplicationFacade: call startup command");
		sendNotification(STARTUP);
	}
}
