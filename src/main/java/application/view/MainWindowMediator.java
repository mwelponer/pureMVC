package application.view;

import java.awt.Dimension;

import org.puremvc.java.multicore.interfaces.IMediator;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

import application.ApplicationFacade;
import application.model.ItemProxy;
import application.model.vo.ItemVO;


public class MainWindowMediator extends Mediator implements IMediator {

	private static final String NAME = "MainWindowMediator";
	private static MainWindow mw = null;
	private ItemProxy itemProxy = null;
	
	public MainWindowMediator() {
		super(NAME, null);
		
		System.out.println("MainWindowMediator: instantiate and attach MainWindow component");
		mw = new MainWindow(this);
		setViewComponent(mw);
	}
	
	@Override
	public void onRegister() {
		// TODO Auto-generated method stub
		System.out.println("MainWindowMediator: attach the ItemProxy");
		itemProxy = (ItemProxy) getFacade().retrieveProxy(ItemProxy.NAME);
		super.onRegister();
	}
	
	@Override
	public String[] listNotificationInterests() {
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
		// TODO Auto-generated method stub
		
		switch(notification.getName()) {
			case ApplicationFacade.LOAD_ITEMS:
				System.out.println("MainWindowMediator: LOAD_ITEMS, retrieve items from proxy and update view");
				
        		mw.clearTextField();
        		mw.clearTextArea();
				
				for (ItemVO itemVO : itemProxy.items()) {
					mw.insertText(itemVO);
				}
		
				break;
				
        	case ApplicationFacade.SHOW_MAIN_WINDOW:
        		System.out.println("MainWindowMediator: SHOW_MAIN_WINDOW, set and visible true");
        		mw.setPreferredSize(new Dimension(400, 300));
        		mw.pack();
        		mw.setVisible(true);
        		
        		break;
        		
        	case ApplicationFacade.ITEM_ADDED:
        		sendNotification(ApplicationFacade.LOAD_ITEMS);
        		
				break;
            
        	default:
        		break;
		}
            
    	//super.handleNotification(notification);
    }

}