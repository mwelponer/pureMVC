package eu.it.welponer.testPuremvc.controller.messages;

import eu.it.welponer.testPuremvc.ApplicationFacade;
import eu.it.welponer.testPuremvc.model.messages.MessageProxy;
import eu.it.welponer.testPuremvc.model.messages.MessageVO;
import eu.it.welponer.testPuremvc.view.MainWindowMediator;
import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class ReceiveMessageCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(INotification notification) {
        System.out.println("  ReceiveMessageCommand: execute()");

        MessageVO message = (MessageVO)notification.getBody();
        MessageProxy messageProxy = (MessageProxy) getFacade().retrieveProxy(MessageProxy.NAME);

        messageProxy.addMessage(message);

        sendNotification(ApplicationFacade.LOAD_MESSAGES);

        MainWindowMediator mainWindowMediator = (MainWindowMediator) getFacade().retrieveMediator(MainWindowMediator.NAME);
        mainWindowMediator.setLock(false);
    }
}
