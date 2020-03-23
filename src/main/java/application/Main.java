package application;

public class Main {
	
	public void start() {
		ApplicationFacade.getInstance().startup();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Main: inside main()");
        Main m = new Main();
        m.start();
	}

}
