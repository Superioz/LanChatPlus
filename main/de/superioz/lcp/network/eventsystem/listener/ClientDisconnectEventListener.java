package main.de.superioz.lcp.network.eventsystem.listener;

import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.messaging.cmdsystem.ChatCommand;
import main.de.superioz.lcp.network.eventsystem.NetworkEventListener;
import main.de.superioz.lcp.network.eventsystem.events.ClientDisconnectEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetEventHandler;
import main.de.superioz.lcp.util.connection.PacketUtil;

/**
 * Class created on März in 2015
 */
public class ClientDisconnectEventListener implements NetworkEventListener {

    @NetEventHandler
    public void handle(ClientDisconnectEvent event){
        Main.network.getClientChannel().out()
                .send(PacketUtil.make(event.getClientName(), ChatCommand.GOODBYE, event.isHost() + ""));
    }

}
