package application.model.messages;

import org.puremvc.java.multicore.interfaces.IProxy;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

import java.util.ArrayList;

public class MessageProxy extends Proxy implements IProxy {
    public static final String NAME = "MessageProxy";
    private static ArrayList<MessageVO> data = new ArrayList<MessageVO>();

    public MessageProxy() {
        super(NAME, data);
        System.out.println("MessageProxy()");
    }

    public final ArrayList<MessageVO> messages() {
        return (ArrayList<MessageVO>) this.data;
    }

    public final void addMessage(final MessageVO message) {
        System.out.println("  MessageProxy: addMessage()");
        messages().add(message);
    }

    public final void clearMessages(){this.data.clear();}
}
