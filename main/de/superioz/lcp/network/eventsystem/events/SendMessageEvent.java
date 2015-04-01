package main.de.superioz.lcp.network.eventsystem.events;

import main.de.superioz.lcp.network.eventsystem.NetworkEvent;

/**
 * Class created on März in 2015
 */
public class SendMessageEvent extends NetworkEvent {

    private String clientName;

    public SendMessageEvent(String clientName){
        this.clientName = clientName;
    }

    public String getClientName(){
        return clientName;
    }

}
