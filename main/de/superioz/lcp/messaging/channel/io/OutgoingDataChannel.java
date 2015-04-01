package main.de.superioz.lcp.messaging.channel.io;

import main.de.superioz.lcp.messaging.packet.DataPacket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class created on März in 2015
 */
public class OutgoingDataChannel {

    protected Socket receiver;
    protected PrintWriter stream;

    public OutgoingDataChannel(Socket receiver){
        this.receiver = receiver;
        try{
            this.stream = new PrintWriter(receiver.getOutputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public Socket getReceiver(){
        return receiver;
    }

    public PrintWriter getStream(){
        return stream;
    }

    public void send(DataPacket packet){
        this.stream.println(packet.toString());
        this.stream.flush();
    }

}
