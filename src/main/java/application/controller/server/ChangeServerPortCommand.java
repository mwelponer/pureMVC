package application.controller.server;

import org.puremvc.java.multicore.interfaces.ICommand;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class ChangeServerPortCommand extends SimpleCommand implements ICommand {

    @Override
    public final void execute(INotification notification) {
        // stop server

        // change ServerPreferencesProxy

        // start server again

    }
}
