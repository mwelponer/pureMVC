package application;

import application.model.server.ServerProxy;

public class Main {
	
	public void start() {
		ApplicationFacade.getInstance().startup();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("main()");
        Main m = new Main();
        m.start();

//        ServerProxy server = (ServerProxy)ApplicationFacade.getInstance().retrieveProxy("ServerProxy");
//		new Thread(server).start();
	}

}
