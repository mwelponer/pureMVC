/*
 * Copyright (C) 2020  Michele Welponer, mwelponer@gmail.com (Fondazione Bruno Kessler)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/> and file GPL3.txt
 */

package eu.it.welponer.testPuremvc;

import eu.it.welponer.testPuremvc.controller.StartupCommand;
import eu.it.welponer.testPuremvc.controller.messages.ClearMessagesCommand;
import eu.it.welponer.testPuremvc.controller.messages.ReceiveMessageCommand;
import eu.it.welponer.testPuremvc.controller.messages.SendMessageCommand;
import eu.it.welponer.testPuremvc.controller.server.ChangeServerPortCommand;
import eu.it.welponer.testPuremvc.controller.server.StartServerCommand;
import org.puremvc.java.multicore.interfaces.IFacade;
import org.puremvc.java.multicore.patterns.facade.Facade;


public class ApplicationFacade extends Facade implements IFacade {

	private static ApplicationFacade instance = new ApplicationFacade();
	public static final String NAME = "ApplicationFacade";
	public static final String STARTUP = "startup";
	
	//public static final String ADD_ITEM = "addItem";
	
//	public static final String ADD_ITEM = "AddItem";
//	public static final String ITEM_ADDED = "ItemAdded";
//	public static final String LOAD_ITEMS = "LoadItems";

	public static final String START_SERVER = "start_server";
	public static final String SERVER_STARTED = "server_started";
	public static final String SEND_MESSAGE = "send_message";
	public static final String MESSAGE_SENT = "message_sent";
	public static final String RECEIVE_MESSAGE = "receive_message";
	public static final String UPDATE_CONSOLE = "update_console";
	public static final String UPDATE_STATUSBAR = "update_statusBar";
	public static final String LOAD_MESSAGES = "load_messages";
	public static final String CLEAR_MESSAGES = "clear_messages";
	public static final String CHANGE_SERVER_PORT = "change_server_port";
	public static final String SET_DEBUG_LEVEL = "set_debug_level";

	public static final String SHOW_MAIN_WINDOW = "show_main_window";
	public static final String SHUTDOWN = "shutdown";


	////////////////////////////////////
	// THIRD
	////////////////////////////////////
	protected ApplicationFacade() {
		super(NAME);
		System.out.println("ApplicationFacade()");
	}

	////////////////////////////////////
	// SECOND
	////////////////////////////////////
	public static ApplicationFacade getInstance() { return instance;}

	////////////////////////////////////
	// FIRST
	////////////////////////////////////
	@Override
	protected void initializeController() {
		// TODO Auto-generated method stub
		super.initializeController();
		System.out.println("  ApplicationFacade: initializeController()");
		registerCommand(STARTUP, new StartupCommand());
		registerCommand(START_SERVER, new StartServerCommand());
		registerCommand(SEND_MESSAGE, new SendMessageCommand());
		registerCommand(RECEIVE_MESSAGE, new ReceiveMessageCommand());
		registerCommand(CLEAR_MESSAGES, new ClearMessagesCommand());
		registerCommand(CHANGE_SERVER_PORT, new ChangeServerPortCommand());
	}
	
	public void startup() {
		// TODO Auto-generated method stub
		System.out.println("  ApplicationFacade: startup()");
		sendNotification(STARTUP);
	}
}
