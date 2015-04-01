package main.de.superioz.lcp.network;

import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.gui.popup.PopupManager;
import main.de.superioz.lcp.logging.ConsoleLogger;
import main.de.superioz.lcp.logging.LoggingLevel;
import main.de.superioz.lcp.messaging.ClientHandler;
import main.de.superioz.lcp.messaging.channel.DataChannel;
import main.de.superioz.lcp.network.eventsystem.events.ClientConnectEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetworkEventManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class created on März in 2015
 */
public class NetworkClient {

    private Socket client;
    private DataChannel channel;

    /**
     * Connects the client to a networkServer
     *
     * @param server The client will try to connect to this server
     * @return If the client could connect
     */
    public boolean connect(NetworkServer server){
        try {
            this.client = new Socket(server.getServerIP().getValue(), server.getServerPort().getValue());
            this.channel = new DataChannel().init(this.client);

            // Throw join event
            NetworkEventManager.fireEvent(new ClientConnectEvent(Main.network.getClientsName(), this.client));

            // Log to console
            ConsoleLogger.println(LoggingLevel.INFORMATION, true
                    , "You (" + Main.network.getClientsName() + "[@" + this.getAddress() + "]) are now connected to the network!");
            return true;
        }catch(Exception ex){
            PopupManager.popupError("Error while connecting to server!", "", ex, Main.chatInterface);
            ConsoleLogger.println(LoggingLevel.ERROR, true, "You couldn't connect!");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Starts listening if the server sends packets to it
     * IMPORTANT! The client should be connected to a server
     */
    public void listen(){
        Thread thr = new Thread(new ClientHandler(channel()));

        // Put thread into threadlist of network
        Main.network.addThread(thr);

        thr.start();
    }

    /**
     * Checks if the socket is connected to another
     *
     * @return The boolean value of this statement
     */
    public boolean isConnected(){
        return this.getSocket().isConnected();
    }

    /**
     * Returns the connected serversocket if connected
     *
     * @return The serversocket object
     */
    public ServerSocket getConnection(){
        if(this.isConnected())
            return Network.getNetworkServer().getSocket();
        else
            return null;
    }

    /**
     * Closes the socket of this client
     */
    public void disconnect(){
        if(this.isConnected()){
            try{
                this.getSocket().close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket(){
        return client;
    }

    public DataChannel channel(){
        return channel;
    }

    /**
     * @return The inet address of client in a string
     */
    public String getAddress() throws UnknownHostException{
        return InetAddress.getLocalHost().getHostAddress();
    }

    @Override
    public String toString(){
        return "Client @"+client.getRemoteSocketAddress()+"; " +
                "Channel:"+channel.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof NetworkClient){
            NetworkClient otherClient = (NetworkClient) obj;

            return otherClient.channel().equals(this.channel())
                    && otherClient.getSocket().equals(this.getSocket());
        }

        return false;
    }

}
