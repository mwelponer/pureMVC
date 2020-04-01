package eu.it.welponer.testPuremvc;

import eu.it.welponer.testPuremvc.utils.OsUtils;

import javax.swing.*;

public class Main {
	
	public void start() {
		ApplicationFacade.getInstance().startup();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
