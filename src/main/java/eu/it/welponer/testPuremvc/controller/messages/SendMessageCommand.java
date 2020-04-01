package eu.it.welponer.testPuremvc.controller.messages;

import eu.it.welponer.testPuremvc.ApplicationFacade;
import eu.it.welponer.testPuremvc.model.client.ClientProxy;
import eu.it.welponer.testPuremvc.model.messages.MessageVO;
import eu.it.welponer.testPuremvc.view.MainWindowMediator;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class SendMessageCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(INotification notification) {
        System.out.println("  SendMessageCommand: execute()");

        MainWindowMediator mainWindowMediator = (MainWindowMediator) getFacade().retrieveMediator(MainWindowMediator.NAME);
        mainWindowMediator.setLock(true);

        MessageVO message = (MessageVO) notification.getBody();
        ClientProxy clientProxy = (ClientProxy) getFacade().retrieveProxy(ClientProxy.NAME);

        String response = clientProxy.sendMessage(message);
        sendNotification(ApplicationFacade.MESSAGE_SENT, response);
    }
}
