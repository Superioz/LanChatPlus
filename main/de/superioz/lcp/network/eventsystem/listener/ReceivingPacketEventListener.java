package main.de.superioz.lcp.network.eventsystem.listener;

import javafx.application.Platform;
import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.chat.MessagePattern;
import main.de.superioz.lcp.gui.popup.PopupManager;
import main.de.superioz.lcp.messaging.cmdsystem.ChatCommand;
import main.de.superioz.lcp.messaging.packet.DataPacket;
import main.de.superioz.lcp.network.Network;
import main.de.superioz.lcp.network.actions.NetInstruction;
import main.de.superioz.lcp.network.eventsystem.NetworkEventListener;
import main.de.superioz.lcp.network.eventsystem.events.ReceivingPacketEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetEventHandler;
import main.de.superioz.lcp.util.CustomFileManager;
import main.de.superioz.lcp.util.connection.PacketUtil;

/**
 * Class created on März in 2015
 */
public class ReceivingPacketEventListener implements NetworkEventListener {

    @NetEventHandler
    public void  handleReceivingPacketEvent(ReceivingPacketEvent event){
        DataPacket packet = event.getReceivedPacket();
        NetInstruction instruction = packet.getContent();
        ChatCommand command = ChatCommand.getFromLine(instruction.getCommandLine());

        if(command != null){
            switch(command){
                /*
                Default commands for sending messages
                 */
                case BROADCAST:
                    // Broadcasts the message from command context to all players
                    Network.getNetworkServer()
                            .send(PacketUtil.make(instruction.getClientsName(),
                                    ChatCommand.PRINT_TO_CHAT, instruction.getCommandContent()));
                    break;
                case PRINT_TO_CHAT:
                    // Prints the comand content to chat of client
                    Main.networkChat
                            .sendln(MessagePattern.USER, instruction.getClientsName(), instruction.getCommandContent());
                    break;

                /*
                Join and leave commands of users
                 */
                case USER_JOINED:
                    // Adds the given user to online list and send joined chat message
                    Main.networkChat.sendln(MessagePattern.JOIN_AND_LEAVE, instruction.getClientsName() + " joined this ChatNetwork!");
                    Main.networkChat.onlineList.add(instruction.getClientsName());

                    break;
                case USER_LEFT:
                    // Remove from online list and send message
                    if(instruction.getCommandContent().equals("true")){
                        // Close the network
                        Main.network.disconnectClient(true);
                        Platform.runLater(() ->
                                PopupManager.popupError(CustomFileManager.getTextFile(Main.lang.get("popupNSGoneOfflineFile")) + "\n"
                                        , Main.lang.get("popupNSGoneOffline"), null, Main.chatInterface));

                        return;
                    }

                    // Send message that the user left
                    Main.networkChat.sendln(MessagePattern.JOIN_AND_LEAVE, instruction.getClientsName() + " has left this ChatNetwork!");
                    Main.networkChat.onlineList.remove(instruction.getClientsName());

                    break;

                /*
                Welcome and Goodbye Messages
                 */
                case WELCOME:
                    // New user put it to names list
                    Network.getNetworkServer().clientNames.add(instruction.getClientsName());

                    // Sends all user the command to handle the user joined
                    Network.getNetworkServer()
                            .send(PacketUtil.make(instruction.getClientsName(),
                                    ChatCommand.USER_JOINED, instruction.getCommandContent()));
                    Network.getNetworkServer()
                            .send(PacketUtil.make("-1", ChatCommand.NEW_ONLINE_LIST, Network.getNetworkServer().namesToString()));
                    break;
                case GOODBYE:
                    // Removes the channel from list
                    Network.getNetworkServer().removeFromList(event.getSender());
                    Network.getNetworkServer().clientNames.remove(instruction.getClientsName());

                    // Sends a packet to all clients to handle the left
                    Network.getNetworkServer()
                            .send(PacketUtil.make(instruction.getClientsName(),
                                    ChatCommand.USER_LEFT, instruction.getCommandContent()));
                    break;

                /*
                New online list
                 */
                case NEW_ONLINE_LIST:
                    String[] onlineList = instruction.getCommandContent().split(",");
                    Main.networkChat.onlineList.clear();

                    for(String s : onlineList){
                        Main.networkChat.onlineList.add(s);
                    }

                    Main.networkChat.onlineList.refresh();
                    break;
                default:
                    break;
            }
        }
    }

}
