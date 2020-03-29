package application.controller.messages;

import application.ApplicationFacade;
import application.model.messages.MessageProxy;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class ClearMessagesCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(INotification notification) {
        System.out.println("  ClearMessagesCommand: execute()");

        MessageProxy messageProxy = (MessageProxy) getFacade().retrieveProxy(MessageProxy.NAME);
        messageProxy.clearMessages();

        sendNotification(ApplicationFacade.LOAD_MESSAGES);
    }
}
