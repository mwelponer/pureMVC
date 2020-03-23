package application.controller;

import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import application.ApplicationFacade;
import application.model.ItemProxy;
import application.model.vo.ItemVO;

public class AddItemCommand extends SimpleCommand implements ICommand {

	@Override
	public final void execute(INotification notification) {
		System.out.println("AddItemCommand: call addItem proxy method, notify ITEM_ADDED");
		ItemVO item = (ItemVO) notification.getBody();
		ItemProxy itemProxy = (ItemProxy)getFacade().retrieveProxy(ItemProxy.NAME);
		itemProxy.addItem(item);

		sendNotification(ApplicationFacade.ITEM_ADDED);
		
		//super.execute(notification);
	}
}
