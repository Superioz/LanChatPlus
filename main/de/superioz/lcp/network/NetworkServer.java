package main.de.superioz.lcp.network;

import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.gui.popup.PopupManager;
import main.de.superioz.lcp.logging.ConsoleLogger;
import main.de.superioz.lcp.logging.LoggingLevel;
import main.de.superioz.lcp.messaging.ServerHandler;
import main.de.superioz.lcp.messaging.channel.DataChannel;
import main.de.superioz.lcp.messaging.packet.DataPacket;
import main.de.superioz.lcp.network.connection.IP;
import main.de.superioz.lcp.network.connection.Port;
import main.de.superioz.lcp.util.connection.IPHandler;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Class created on März in 2015
 */
public class NetworkServer {

    private ServerSocket serverSocket;
    private Port serverPort;
    private IP serverIP;

    private ArrayList<DataChannel> clientChannels;
    public ArrayList<String> clientNames;

    public NetworkServer(Port port){
        this.serverPort = port;
        this.serverIP = new IP(IPHandler.LOCALHOST);
    }

    /**
     * Starts the server where the clients can connect
     *
     * @return If it was successful
     */
    public int startServer(){
        try{
            this.serverSocket = new ServerSocket(this.serverPort.getValue());
            ConsoleLogger.println(LoggingLevel.ERROR, true
                    , "@Host >> NetworkServer started! @[" + serverIP + ":" + serverPort.getValue() + "]");
            clientChannels = new ArrayList<>();
            clientNames = new ArrayList<>();
            return 1;
        }catch(BindException ex){
            ConsoleLogger.println(LoggingLevel.ERROR, true
                    , "NetworkServer not started 'cause another server runs on that port");
            return 0;
        }catch (IOException ex){
            PopupManager.popupError("Error while starting the server!", "", ex, Main.chatInterface);
            ConsoleLogger.println(LoggingLevel.ERROR, true
                    , "WARNING! NetworkServer couldn't be started!");
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * Listens to Clients and let them join while the server is online
     * Then start a thread to receive messages and give respond
     */
    public void listen(){
        while(!serverSocket.isClosed()){
            try{
                Socket client = serverSocket.accept();

                DataChannel dataChannel = new DataChannel().init(client);
                clientChannels.add(dataChannel);
                Thread thr = new Thread(new ServerHandler(new DataChannel().init(client)));

                // Put Thread into Threadlist of Network
                Main.network.addThread(thr);
                thr.start();
            }catch(SocketException ignored){
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * Returns if the  server is online
     *
     * @return The boolean value
     */
    public boolean isOnline(){
        return !serverSocket.isClosed();
    }

    /**
     * Closes the server
     */
    public void close(){
        if(isOnline()){
            try{
                serverSocket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Sends a packet to the datachannel
     *
     * @param packet the packet
     */
    public void send(DataPacket packet){
        for(DataChannel ch : this.getClientChannels()){
            ch.out().send(packet);
        }
    }

    public void send(DataPacket packet, DataChannel except){
        this.getClientChannels().stream().filter(ch -> !ch.equals(except)).forEach(ch -> {
            ch.out().send(packet);
        });
    }

    public void removeFromList(DataChannel channel){
        if(this.getClientChannels().contains(channel))
            this.getClientChannels().remove(channel);
    }

    public void addName(String name){
        this.clientNames.add(name);
    }

    public void removeName(String name){
        if(clientNames.contains(name)){
            clientNames.remove(name);
        }
    }

    public String namesToString(){
        String s = "";

        for(int i = 0; i < clientNames.size(); i++){
            if(i != clientNames.size()-1){
                s += clientNames.get(i) + ",";
            }
            else{
                s += clientNames.get(i);
            }
        }

        return s;
    }

    public boolean nobodyOnline(){
        return clientNames.isEmpty();
    }

    public IP getServerIP(){
        return serverIP;
    }

    public Port getServerPort(){
        return serverPort;
    }

    public ServerSocket getSocket(){
        return serverSocket;
    }

    public ArrayList<DataChannel> getClientChannels(){
        return clientChannels;
    }

}
