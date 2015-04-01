package main.de.superioz.lcp.messaging.channel;

import main.de.superioz.lcp.messaging.channel.io.IncomingDataChannel;
import main.de.superioz.lcp.messaging.channel.io.OutgoingDataChannel;

import java.net.Socket;

/**
 * Class created on März in 2015
 */
public class DataChannel {

    private OutgoingDataChannel out;
    private IncomingDataChannel in;

    public DataChannel init(Socket client){
        in = new IncomingDataChannel(client);
        out = new OutgoingDataChannel(client);

        return this;
    }

    /**
     * Returns the incoming messaging channel to check (what he send)
     *
     * @return The messaging channel object
     */
    public IncomingDataChannel in(){
        return in;
    }


    /**
     * Returns the outgoing messaging channel (what he receive)
     *
     * @return The messaging channel object
     */
    public OutgoingDataChannel out(){
        return out;
    }

    @Override
    public boolean equals(Object o){
        return in.getReceiver().equals(out.getReceiver());
    }

    @Override
    public int hashCode(){
        int result = out != null ? out.hashCode() : 0;
        result = 31 * result + (in != null ? in.hashCode() : 0);
        return result;
    }
}
