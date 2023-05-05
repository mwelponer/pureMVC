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

import eu.it.welponer.testPuremvc.utils.OsUtils;

import javax.swing.*;

public class Main {

	public static int debugLevel = 2;

	public void start() {
		ApplicationFacade.getInstance().startup();
	}
	
	public static void main(String[] args) {
		if(Main.debugLevel > 1)
			System.out.println("main()");

		if(OsUtils.isWindows()) {
			try {
				// Set cross-platform Java L&F (also called "Metal")
				UIManager.setLookAndFeel(
						//UIManager.getSystemLookAndFeelClassName());
						"javax.swing.plaf.metal.MetalLookAndFeel");
			} catch (UnsupportedLookAndFeelException e) {
				// handle exception
			} catch (ClassNotFoundException e) {
				// handle exception
			} catch (InstantiationException e) {
				// handle exception
			} catch (IllegalAccessException e) {
				// handle exception
			}
		}

        Main m = new Main();
        m.start();

//        ServerProxy server = (ServerProxy)ApplicationFacade.getInstance().retrieveProxy("ServerProxy");
//		new Thread(server).start();
	}

}
