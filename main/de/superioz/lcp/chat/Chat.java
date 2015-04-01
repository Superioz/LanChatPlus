package main.de.superioz.lcp.chat;

import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.messaging.cmdsystem.ChatCommand;
import main.de.superioz.lcp.util.connection.PacketUtil;

/**
 * Class created on März in 2015
 */
public class Chat {

    public static final char DOLLAR = '$';
    public static final char BACKSLASH = '\\';
    public OnlineList onlineList;

    public Chat(){
        this.onlineList = new OnlineList(Main.chatSceneController.getOnlineUsersArea());
    }

    /**
     * Sends given messages to chat
     *
     * @param text What text should be printed
     */
    public void send(String text){
        Main.chatSceneController.appendTextToChat(text);
    }

    public void send(MessagePattern pattern, String client, String text){
        Main.chatSceneController
                .appendTextToChat(MessagePattern.applyPatternOn(text, client, pattern));
    }

    public void send(MessagePattern pattern, String text){
        Main.chatSceneController
                .appendTextToChat(MessagePattern.applyPatternOn(text, "", pattern));
    }

    /**
     * Sends given messages to chat with line break
     *
     * @param text What text should be sent
     */
    public void sendln(String text){
        send(text + "\n");
    }

    public void sendln(MessagePattern pattern, String client, String text){
        send(pattern, client, text + "\n");
    }

    public void sendln(MessagePattern pattern, String text){
        send(pattern, "", text + "\n");
    }

    /* ===============================
    Methods to send messages from a client
     */

    /**
     * Sends an instruction to the server which contains the command to send a message to all clients
     *
     * @param msg What is the message
     * @param clientName What is the name of the client
     */
    public void sendToServer(String msg, String client){
        String clientName = client;
        if(Main.network.isHost && Main.network.getClientsName().equals(client)){
            clientName = "Host » " + client;
        }

        Main.network.getClientChannel().out()
                .send(PacketUtil.make(clientName, ChatCommand.BROADCAST, msg));
    }

}
