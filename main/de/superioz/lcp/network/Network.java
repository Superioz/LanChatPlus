package main.de.superioz.lcp.network;

import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.logging.ConsoleLogger;
import main.de.superioz.lcp.logging.LoggingLevel;
import main.de.superioz.lcp.messaging.channel.DataChannel;
import main.de.superioz.lcp.network.connection.IP;
import main.de.superioz.lcp.network.connection.Port;
import main.de.superioz.lcp.network.eventsystem.events.ClientDisconnectEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetworkEventManager;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class created on März in 2015
 */
public class Network {

    private static NetworkServer networkServer;
    private static NetworkClient networkClient;
    public List<Thread> networkThreads;
    private Port port;
    private IP ip;
    public boolean isHost = false;

    public Network(Port port, String address){
        this.port = port;
        this.ip = new IP(address);
        this.networkThreads = new ArrayList<>();
    }

    /**
     * Starts the server in a different thread to listen for client AND
     * to connect with this client
     */
    public int startServer(){
        networkServer = new NetworkServer(this.port, this.ip.getValue());
        final int[] respond = new int[1];
        respond[0] = networkServer.startServer();

        // Check if this network is serverhost
        if(respond[0] == 1){
            this.isHost = true;
        }


        Thread thr = new Thread(() -> {
            if(respond[0] == 1){
                networkServer.listen();
            }
        });
        Main.network.addThread(thr);
        thr.start();

        return respond[0];
    }

    /**
     * Connect to the server with this client (application)
     */
    public int connect(){
        networkClient = new NetworkClient();

        if(networkClient.connect(networkServer)){
            networkClient.listen();
            return 1;
        }
        else {
            return -1;
        }
    }

    /**
     * Disconnects the client from the server
     */
    public void disconnectClient(){
        if(networkClient.isConnected()){

            // Throw disconnect event
            NetworkEventManager.fireEvent(new ClientDisconnectEvent(Main.network.getClientsName(), Main.network.isHost));
            networkClient.disconnect();

            // log to console
            ConsoleLogger.println(LoggingLevel.INFORMATION, true
                    , "You (" + Main.network.getClientsName() + "[@" + this.getClientsAddress() + "]) are now disconnected from the Network!");
        }
    }

    public void disconnectClient(boolean silent){
        if(networkClient.isConnected()){

            // Throw disconnect event
            if(!silent){
                NetworkEventManager.fireEvent(new ClientDisconnectEvent(Main.network.getClientsName(), Main.network.isHost));
            }
            networkClient.disconnect();

            // log to console
            ConsoleLogger.println(LoggingLevel.INFORMATION, true
                    , "You (" + Main.network.getClientsName() + "[@" + this.getClientsAddress() + "]) are now disconnected from the Network!");
        }
    }

    /**
     * Closes the server
     */
    public void closeServer(){
        if(isHost){
            networkServer.close();
            Main.startupInput = null;
            ConsoleLogger.println(LoggingLevel.ERROR, true
                    , "@Host >> Networkserver closed!");
        }
    }

    /**
     * Adds a thread to the threadlist to cancel them later
     *
     * @param thread The thread
     */
    public void addThread(Thread thread){
        this.networkThreads.add(thread);
    }

    /**
     * Returns the name of the client given
     */
    public String getClientsName(){
        return Main.startupInput.getName();
    }

    /**
     * Returns the IP Address of the client
     */
    public String getClientsAddress() {
        try{
            return networkClient.getAddress();
        }catch(UnknownHostException e){
            e.printStackTrace();
            return "-1";
        }
    }

    public boolean pingIP(Socket socket){
        try{
            return socket.getInetAddress().isReachable(2000);
        }catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Returns how many clients connected
     */
    public int clientsConnected(){
        return networkServer.getClientChannels().size();
    }

    public List<DataChannel> getClientChannels(){
        return networkServer.getClientChannels();
    }

    public DataChannel getClientChannel(){
        return networkClient.channel();
    }

    public static NetworkServer getNetworkServer(){
        return networkServer;
    }

    public static NetworkClient getNetworkClient(){
        return networkClient;
    }

    public Port getPort(){
        return port;
    }

    public String getIPAddress(){
        return this.ip.getValue() + ":" + getPort().getValue();
    }

}
