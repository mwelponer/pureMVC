package application.view;

import java.awt.Dimension;

import application.model.messages.MessageProxy;
import application.model.messages.MessageVO;
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
	private MessageProxy messageProxy = null;
	
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
		messageProxy = (MessageProxy) getFacade().retrieveProxy(MessageProxy.NAME);
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
			ApplicationFacade.SHOW_MAIN_WINDOW,
            ApplicationFacade.ADD_ITEM,
            ApplicationFacade.ITEM_ADDED,
			ApplicationFacade.LOAD_ITEMS,
			ApplicationFacade.SEND_MESSAGE,
            ApplicationFacade.MESSAGE_SENT,
			ApplicationFacade.UPDATE_CONSOLE,
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
        	case ApplicationFacade.SHOW_MAIN_WINDOW:
				mainWindow.setPreferredSize(new Dimension(400, 300));
				mainWindow.pack();
				mainWindow.setVisible(true);
        		break;

			case ApplicationFacade.SERVER_STARTED:
				break;

        	case ApplicationFacade.ITEM_ADDED:
        		sendNotification(ApplicationFacade.LOAD_ITEMS);
				break;

			case ApplicationFacade.LOAD_ITEMS:
//				mainWindow.clearTextField();
//				mainWindow.clearTextArea();
//
//				for (ItemVO itemVO : itemProxy.items()) {
//					mainWindow.insertText(itemVO);
//				}
				break;

			case ApplicationFacade.MESSAGE_SENT:
				break;

			case ApplicationFacade.UPDATE_CONSOLE:
				mainWindow.writeToOutputConsole((String)notification.getBody());
				break;

			case ApplicationFacade.LOAD_MESSAGES:
				mainWindow.clearOutputConsole();

				for(MessageVO messageVO : messageProxy.messages())
					mainWindow.writeToOutputConsole(messageVO.getJsonObject().toString());
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