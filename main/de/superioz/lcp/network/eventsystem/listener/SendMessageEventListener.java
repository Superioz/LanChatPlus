package main.de.superioz.lcp.network.eventsystem.listener;

import javafx.scene.control.TextField;
import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.chat.Chat;
import main.de.superioz.lcp.gui.scenes.ChatSceneController;
import main.de.superioz.lcp.network.eventsystem.NetworkEventListener;
import main.de.superioz.lcp.network.eventsystem.events.SendMessageEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetEventHandler;

/**
 * Class created on März in 2015
 */
public class SendMessageEventListener implements NetworkEventListener {

    @NetEventHandler
    public void handle(SendMessageEvent event){
        ChatSceneController chatCon = Main.chatSceneController;
        TextField field = chatCon.messageField;

        if(!field.getText().isEmpty()){
            String text = field.getText();
            text = text.replaceAll("\\" + Chat.DOLLAR, "€").replaceAll("\\" + Chat.BACKSLASH, "/");

            Main.networkChat.sendToServer(text, Main.network.getClientsName());
            field.clear();
            field.requestFocus();
        }
    }


}
