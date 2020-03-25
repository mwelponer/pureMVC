package application.controller;

import application.ApplicationFacade;
import application.model.messages.MessageVO;
import application.model.server.RequestMethod;
import application.model.server.ServerProxy;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class SendMessageCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(INotification notification) {
        System.out.println("  SendMessageCommand: execute()");

        MessageVO message = (MessageVO) notification.getBody();
        ServerProxy serverProxy = (ServerProxy) getFacade().retrieveProxy(ServerProxy.NAME);
        serverProxy.sendRequest(message);

        sendNotification(ApplicationFacade.MESSAGE_SENT);
    }
}
