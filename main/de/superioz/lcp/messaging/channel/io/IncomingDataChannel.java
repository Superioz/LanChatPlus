package main.de.superioz.lcp.messaging.channel.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Class created on März in 2015
 */
public class IncomingDataChannel {

    protected Socket receiver;
    protected BufferedReader stream;

    public IncomingDataChannel(Socket receiver){
        this.receiver = receiver;
        try{
            this.stream = new BufferedReader(new InputStreamReader(receiver.getInputStream()));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public Socket getReceiver(){
        return receiver;
    }

    public BufferedReader getStream(){
        return stream;
    }

}
