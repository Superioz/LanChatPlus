package main.de.superioz.lcp.network.eventsystem.events;

import main.de.superioz.lcp.network.eventsystem.NetworkEvent;

import java.net.Socket;

/**
 * Class created on März in 2015
 */
public class ClientConnectEvent extends NetworkEvent {

    private Socket clientSocket;
    private String clientName;

    public ClientConnectEvent(String clientName, Socket clientSocket){
        this.clientName = clientName;
        this.clientSocket = clientSocket;
    }

    public String getClientName(){
        return clientName;
    }

    public Socket getClientSocket(){
        return clientSocket;
    }

}
