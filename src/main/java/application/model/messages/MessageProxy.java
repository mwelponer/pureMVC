package application.model.messages;

import org.puremvc.java.multicore.interfaces.IProxy;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

import java.util.ArrayList;

public class MessageProxy extends Proxy implements IProxy {
    public static final String NAME = "JSONMessageProxy";

    public MessageProxy() {
        super(NAME, new ArrayList<MessageVO>());
        System.out.println("JSONMessageProxy()");
    }

    public final ArrayList<MessageVO> messages() {
        return (ArrayList<MessageVO>) this.getData();
    }

    public final void addMessage(final MessageVO message) {
        System.out.println("  JSONMessageProxy: addMessage()");
        messages().add(message);
    }
}
