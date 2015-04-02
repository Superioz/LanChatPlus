package main.de.superioz.lcp.gui.scenes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.gui.popup.PopupManager;
import main.de.superioz.lcp.network.eventsystem.events.SendMessageEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetworkEventManager;
import main.de.superioz.lcp.util.CustomFileManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class created on März in 2015
 */
public class ChatSceneController implements Initializable {

    @FXML public TextArea mainChatWindow;
    @FXML public TextArea onlineUsers;

    @FXML public TextField messageField;

    @FXML public Button sendMessage;
    @FXML public Button cancelMessage;

    @FXML public Menu connection;
    @FXML public Menu program;
    @FXML public Menu help;

    @FXML public MenuItem quitToMenuItem;
    @FXML public MenuItem yourProfileItem;
    @FXML public MenuItem serversProfileItem;

    @FXML public MenuItem exitItem;
    @FXML public MenuItem aboutItem;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.init();
    }

    /**
     * Init the stage
     */
    public void init(){
        connection.setText(Main.lang.get("chatConnectionMenu"));
        program.setText(Main.lang.get("chatProgramMenu"));
        help.setText(Main.lang.get("chatHelpMenu"));

        quitToMenuItem.setText(Main.lang.get("chatQuitItem"));
        yourProfileItem.setText(Main.lang.get("chatYourProfileItem"));
        serversProfileItem.setText(Main.lang.get("chatServersProfileItem"));

        exitItem.setText(Main.lang.get("chatExitItem"));
        aboutItem.setText(Main.lang.get("chatAboutItem"));

        cancelMessage.setText(Main.lang.get("chatCancelButton"));
    }

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
                , getClass().getResource("/main/resources/icons/clientProfile.png").toExternalForm()
                , Main.lang.get("chatYourProfileItem"), Main.lang.get("chatYourProfileItem"),
                "Username: " + Main.network.getClientsName() +
                "\nComputer-Name: " + Main.getComputername() +
                "\nIP-Address: " + Main.network.getClientsAddress());
    }

    @FXML
    public void handleServerProfile(){
        PopupManager.popupCustom(Main.chatInterface
                , getClass().getResource("/main/resources/icons/serverProfile.png").toExternalForm()
                , Main.lang.get("chatYourProfileItem"), Main.lang.get("chatYourProfileItem"),
                "IP-Address: " + Main.network.getIPAddress());
    }

    @FXML
    public void handleExitProgram(){
        Main.guiManager.fireCloseEvent(Main.chatInterface);
    }

    @FXML
    public void handleAbout(){
        PopupManager.popupCustom(Main.chatInterface
                , getClass().getResource("/main/resources/icons/aboutIcon.png").toExternalForm()
                , Main.lang.get("chatAboutPopup"), Main.lang.get("chatAboutItem"), CustomFileManager.getTextFile(Main.lang.get("chatAboutTextfile")));
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
