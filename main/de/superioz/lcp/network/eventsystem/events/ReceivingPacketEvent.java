package main.de.superioz.lcp.network.eventsystem.events;

import main.de.superioz.lcp.messaging.channel.DataChannel;
import main.de.superioz.lcp.messaging.packet.DataPacket;
import main.de.superioz.lcp.network.eventsystem.NetworkEvent;

/**
 * Class created on März in 2015
 */
public class ReceivingPacketEvent extends NetworkEvent {

    private DataPacket receivedPacket;
    private DataChannel sender;

    public ReceivingPacketEvent(DataPacket packet, DataChannel sender){
        this.receivedPacket = packet;
    }

    public DataPacket getReceivedPacket(){
        return receivedPacket;
    }

    public DataChannel getSender(){
        return sender;
    }
}
