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

package eu.it.welponer.testPuremvc.controller.server;

import eu.it.welponer.testPuremvc.ApplicationFacade;
import eu.it.welponer.testPuremvc.model.server.ServerPreferencesProxy;
import eu.it.welponer.testPuremvc.model.server.ServerProxy;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class StartServerCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(INotification notification) {
        System.out.println("  StartServerCommand: execute()");

        ServerPreferencesProxy serverPreferencesProxy = (ServerPreferencesProxy)notification.getBody();

        ServerProxy serverProxy = new ServerProxy(serverPreferencesProxy.getServerPrefs());
        getFacade().registerProxy(serverProxy);

        new Thread(serverProxy).start();
        sendNotification(ApplicationFacade.SERVER_STARTED, serverPreferencesProxy.getServerPrefs().getServerPort());

        sendNotification(ApplicationFacade.UPDATE_STATUSBAR, "server listening on port "
                + serverPreferencesProxy.getServerPrefs().getServerPort());
    }
}
