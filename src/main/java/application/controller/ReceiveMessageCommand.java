package application.controller;

import application.ApplicationFacade;
import application.model.messages.MessageProxy;
import application.model.messages.MessageVO;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class ReceiveMessageCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(INotification notification) {
        System.out.println("  ReceiveJSONCommand: execute()");

        MessageVO message = (MessageVO) notification.getBody();
        MessageProxy messageProxy = (MessageProxy) getFacade().retrieveProxy(MessageProxy.NAME);
        messageProxy.addMessage(message);

        sendNotification(ApplicationFacade.MESSAGE_ADDED);

        //super.execute(notification);
    }
}
