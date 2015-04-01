package main.de.superioz.lcp.network.eventsystem.events;

import main.de.superioz.lcp.network.eventsystem.NetworkEvent;

/**
 * Class created on März in 2015
 */
public class ClientDisconnectEvent extends NetworkEvent {

    private String clientName;
    private boolean isHost;

    public ClientDisconnectEvent(String name, boolean isHost){
        this.clientName = name;
        this.isHost = isHost;
    }

    public String getClientName(){
        return clientName;
    }

    public boolean isHost(){
        return this.isHost;
    }

}
