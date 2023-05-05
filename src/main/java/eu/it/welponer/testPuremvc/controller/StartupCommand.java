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
