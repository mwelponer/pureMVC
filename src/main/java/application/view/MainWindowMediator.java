package application.view;

import java.awt.Dimension;

import org.puremvc.java.multicore.interfaces.IMediator;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

import application.ApplicationFacade;
import application.model.items.ItemProxy;
import application.model.items.ItemVO;


public class MainWindowMediator extends Mediator implements IMediator {

	private static final String NAME = "MainWindowMediator";
	private static MainWindow mainWindow = null;
	private ItemProxy itemProxy = null;
	
	public MainWindowMediator() {
		super(NAME, null);
		
		System.out.println("MainWindowMediator()");
		mainWindow = new MainWindow(this);
		setViewComponent(mainWindow);
	}

	/**
	 * Called by the View when the Mediator is registered
	 */
	@Override
	public void onRegister() {
		// TODO Auto-generated method stub
		System.out.println("  MainWindowMediator: onRegister()");
		itemProxy = (ItemProxy) getFacade().retrieveProxy(ItemProxy.NAME);
		super.onRegister();
	}

	/**
	 * List the INotification names this Mediator is interested in being notified of.
	 */
	@Override
	public String[] listNotificationInterests() {
		System.out.println("  MainWindowMediator: listNotificationInterests()");
		// TODO Auto-generated method stub
		return new String[] {
			ApplicationFacade.LOAD_ITEMS,
			ApplicationFacade.SHOW_MAIN_WINDOW,
               
            ApplicationFacade.ADD_ITEM,
            ApplicationFacade.ITEM_ADDED,
                
            ApplicationFacade.SHUTDOWN
		};
		
		//return super.listNotificationInterests();
	}
	
	@Override
	public void handleNotification(INotification notification) {
		System.out.println("  MainWindowMediator: handleNotification(): " +
				notification.getName().toUpperCase());

		// TODO Auto-generated method stub
		switch(notification.getName()) {
			case ApplicationFacade.LOAD_ITEMS:
//				System.out.println("MainWindowMediator: LOAD_ITEMS, retrieve items from proxy and update view");

				mainWindow.clearTextField();
				mainWindow.clearTextArea();
				
				for (ItemVO itemVO : itemProxy.items()) {
					mainWindow.insertText(itemVO);
				}
		
				break;
				
        	case ApplicationFacade.SHOW_MAIN_WINDOW:
//        		System.out.println("MainWindowMediator: SHOW_MAIN_WINDOW, set and visible true");
				mainWindow.setPreferredSize(new Dimension(400, 300));
				mainWindow.pack();
				mainWindow.setVisible(true);
        		
        		break;
        		
        	case ApplicationFacade.ITEM_ADDED:
        		sendNotification(ApplicationFacade.LOAD_ITEMS);
        		
				break;

			case ApplicationFacade.SHUTDOWN:
				mainWindow.dispose();
				System.exit(0);
				break;
            
        	default:
        		break;
		}
            
    	//super.handleNotification(notification);
    }
}