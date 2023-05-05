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
