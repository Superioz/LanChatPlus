package main.de.superioz.lcp.messaging;

import main.de.superioz.lcp.messaging.channel.DataChannel;
import main.de.superioz.lcp.messaging.packet.ChannelListener;
import main.de.superioz.lcp.network.eventsystem.events.ReceivingPacketEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetworkEventManager;
import main.de.superioz.lcp.util.connection.PacketUtil;

import java.io.IOException;
import java.net.SocketException;

/**
 * Class created on März in 2015
 */
public class ServerHandler extends ChannelListener {

    /**
     * Constructor of super class to set variables for receiving
     *
     * @param channel the messaging channel of the client
     */
    public ServerHandler(DataChannel channel){
        super(channel);
    }

    @Override
    public void run(){
        String streamContent;

        try{
            while((streamContent = reader.readLine()) != null){
                // throw eventre
                NetworkEventManager.fireEvent(new ReceivingPacketEvent(PacketUtil.fromString(streamContent), channel));
            }
        }catch(SocketException ignored){
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
