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

package eu.it.welponer.testPuremvc.model.messages;

import org.puremvc.java.multicore.interfaces.IProxy;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

import java.util.ArrayList;

public class MessageProxy extends Proxy implements IProxy {
    public static final String NAME = "MessageProxy";
    private static ArrayList<MessageVO> data = new ArrayList<MessageVO>();

    public MessageProxy() {
        super(NAME, data);
        System.out.println("MessageProxy()");
    }

    public final ArrayList<MessageVO> messages() {
        return (ArrayList<MessageVO>) this.data;
    }

    public final void addMessage(final MessageVO message) {
        System.out.println("  MessageProxy: addMessage()");
        messages().add(message);
    }

    public final void clearMessages(){this.data.clear();}
}
