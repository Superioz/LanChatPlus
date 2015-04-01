package main.de.superioz.lcp.messaging.packet;

import main.de.superioz.lcp.messaging.channel.DataChannel;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Class created on März in 2015
 */
public abstract class ChannelListener implements Runnable {

    /**
     * Reader and packets
     */
    protected BufferedReader reader;
    protected PrintWriter writer;
    protected DataChannel channel;

    public ChannelListener(DataChannel channel){
        this.channel = channel;
        this.reader = channel.in().getStream();
        this.writer = channel.out().getStream();
    }


}
