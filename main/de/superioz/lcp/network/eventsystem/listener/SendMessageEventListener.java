package main.de.superioz.lcp.network.eventsystem.listener;

import javafx.scene.control.TextField;
import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.chat.Chat;
import main.de.superioz.lcp.gui.scenes.ChatSceneController;
import main.de.superioz.lcp.messaging.cmdsystem.ChatCommand;
import main.de.superioz.lcp.network.eventsystem.NetworkEventListener;
import main.de.superioz.lcp.network.eventsystem.events.SendMessageEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetEventHandler;
import main.de.superioz.lcp.util.connection.PacketUtil;

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

            if(text.startsWith("@")){
                // Private Nachricht
                String name = text.split(" ")[0].replaceFirst("@", "");
                String message = text.replaceFirst("@" + name + " ", "");

                if(Main.networkChat.onlineList.contains(name)){
                    Main.network.getClientChannel().out()
                            .send(PacketUtil.make(Main.network.getClientsName(), ChatCommand.SEND_TO, text));
                    Main.networkChat.printPrivateChatTo(name, message);
                    this.clear();
                    return;
                }
            }

            Main.networkChat.sendToServer(text, Main.network.getClientsName());
            this.clear();
        }
    }

    public void clear(){
        ChatSceneController chatCon = Main.chatSceneController;
        TextField field = chatCon.messageField;
        field.clear();
        field.requestFocus();
    }

}
