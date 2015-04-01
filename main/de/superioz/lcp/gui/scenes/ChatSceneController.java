package main.de.superioz.lcp.gui.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.gui.popup.PopupManager;
import main.de.superioz.lcp.network.eventsystem.events.SendMessageEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetworkEventManager;
import main.de.superioz.lcp.util.CustomFileManager;

/**
 * Class created on März in 2015
 */
public class ChatSceneController {

    @FXML public TextArea mainChatWindow;
    @FXML public TextArea onlineUsers;

    @FXML public TextField messageField;

    @FXML public Button sendMessage;
    @FXML public Button cancelMessage;

    /**
     * Sends the message typed in the textfield
     */
    @FXML
    public void handleSendMessage(){
        // Throw event
        NetworkEventManager.fireEvent(new SendMessageEvent(Main.network.getClientsName()));
    }

    @FXML
    public void handleCancelMessage(){
        this.messageField.clear();
    }

    @FXML
    public void handleQuitConnection(){
        Main.guiManager.backToMainMenu(Main.chatInterface);
    }

    @FXML
    public void handleYourProfile(){
        PopupManager.popupCustom(Main.chatInterface
                , getClass().getResource("/main/ressources/icons/clientProfile.png").toExternalForm()
                , "Your Profile", "Your Profile",
                "Username: " + Main.network.getClientsName() +
                "\nComputer-Name: " + Main.getComputername() +
                "\nIP-Address: " + Main.network.getClientsAddress());
    }

    @FXML
    public void handleServerProfile(){
        PopupManager.popupCustom(Main.chatInterface
                , getClass().getResource("/main/ressources/icons/serverProfile.png").toExternalForm()
                , "Server Profile", "Server Profile",
                        "IP-Address: " + Main.network.getIPAddress());
    }

    @FXML
    public void handleExitProgram(){
        Main.guiManager.fireCloseEvent(Main.chatInterface);
    }

    @FXML
    public void handleAbout(){
        PopupManager.popupCustom(Main.chatInterface
                , getClass().getResource("/main/ressources/icons/aboutIcon.png").toExternalForm()
                , "About Dialog", "About", CustomFileManager.getTextFile("about"));
    }

    /**
     * Appends text to textArea
     *
     * @param text The text in string
     */
    public void appendTextToChat(String text){
        this.mainChatWindow.appendText(text);
    }

    /**
     * Returns the text area for the online users
     */
    public TextArea getOnlineUsersArea(){
        return this.onlineUsers;
    }

}
