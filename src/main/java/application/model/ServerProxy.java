package application.model;

import application.model.vo.ServerPreferencesVO;
import org.puremvc.java.multicore.interfaces.IProxy;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

public class ServerProxy extends Proxy implements IProxy, Runnable {

    public static final String NAME = "ServerProxy";

    public ServerProxy(ServerPreferencesVO prefs) {
        super(NAME, prefs);
    }

    @Override
    public void run() {

    }
}
