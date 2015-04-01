package main.de.superioz.lcp.network.eventsystem.listener;

import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.chat.Chat;
import main.de.superioz.lcp.chat.MessagePattern;
import main.de.superioz.lcp.network.Network;
import main.de.superioz.lcp.network.eventsystem.NetworkEventListener;
import main.de.superioz.lcp.network.eventsystem.events.LaunchChatEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetEventHandler;

/**
 * Class created on März in 2015
 */
public class LaunchChatEventListener implements NetworkEventListener {

    @NetEventHandler
    public void handleLaunchEvent(LaunchChatEvent event){
        // Close primary stage, open Chat Window
        Main.primaryStage.close();
        Main.chatInterface.show();
        Main.chatSceneController.messageField.requestFocus();

        // Get chat
        Chat chat = Main.networkChat;

        // Start the server
        chat.sendln(MessagePattern.SERVER, "Starting server ..");

        Main.network = new Network(event.getStartupInput().getPort(), event.getStartupInput().getIp());
        int serverRespond = Main.network.startServer();

        switch(serverRespond){
            case 1:
                // Server started
                chat.sendln(MessagePattern.SERVER
                        , "Server started [@" + Main.network.getIPAddress() + "]. Connect ..");
                break;
            case 0:
                // Server isnt started because another server is running on that port
                chat.sendln(MessagePattern.SERVER
                        , "Server not started 'cause a server is already running on that port. Connect to it ..");
                break;
            case -1:
                chat.sendln(MessagePattern.SERVER
                        , "Couldn't start a server 'cause an error! Back to menu ..");

                Main.guiManager.backToMainMenu(Main.chatInterface);
                break;
            default: break;
        }

        // Connect to server
        int clientRespond = Main.network.connect();

        switch(clientRespond){
            case 1:
                chat.sendln(MessagePattern.SERVER
                        , "Connected to the server.");
                break;
            case -1:
                Main.guiManager.backToMainMenu(Main.chatInterface);
                break;
        }
    }

}
