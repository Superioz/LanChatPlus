package main.de.superioz.lcp.network.eventsystem.events;

import main.de.superioz.lcp.gui.container.StartupInput;
import main.de.superioz.lcp.network.eventsystem.NetworkEvent;

/**
 * Class created on März in 2015
 */
public class LaunchChatEvent extends NetworkEvent {

    private StartupInput startupInput;

    public LaunchChatEvent(StartupInput startupInput){
        this.startupInput = startupInput;
    }

    public StartupInput getStartupInput(){
        return startupInput;
    }
}
