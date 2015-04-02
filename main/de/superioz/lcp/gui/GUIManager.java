package main.de.superioz.lcp.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.logging.ConsoleLogger;
import main.de.superioz.lcp.logging.LoggingLevel;
import main.de.superioz.lcp.network.eventsystem.events.SendMessageEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetworkEventManager;

import java.io.IOException;

/**
 * Class created on März in 2015
 */
public class GUIManager {

    // Main
    public static Main main;

    public GUIManager(Main m){
        main = m;

        try{
            this.setupChatInterface();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Adds and pos objects at/to the primary stage, where you choose
     * the port and your name
     */
    public void setupStartInterface() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent fxmlScene = loader.load(getClass().getResource("scenes/startMenu.fxml").openStream());
        Scene scene = new Scene(fxmlScene);
        scene.getStylesheets().add("/main/resources/style.css");

        Main.primaryStage.setScene(scene);
        Main.primaryStageController = loader.getController();
    }

    /**
     * Setups and shows the chatinterface
     */
    public void setupChatInterface() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent fxmlScene = loader.load(getClass().getResource("scenes/chatScene.fxml").openStream());
        Scene scene = new Scene(fxmlScene);
        scene.getStylesheets().add("/main/resources/style.css");

        // Creating stage
        Stage stage = new Stage();
        stage.setTitle(getChildTitle("Chat Interface"));
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/icons/stageIcon.png")));

        // Sizing of stage
        stage.sizeToScene();
        stage.setResizable(false);
        stage.initOwner(Main.primaryStage);
        this.addCloseRequestEvent(stage);
        Main.chatInterface = stage;
        Main.chatSceneController = loader.getController();
        this.addChatKeyEvent();
    }

    public void setupSettingsWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent fxmlScene = loader.load(getClass().getResource("scenes/settings.fxml").openStream());
        Scene scene = new Scene(fxmlScene);
        scene.getStylesheets().add("/main/resources/style.css");

        // Creating stage
        Stage stage = new Stage();
        stage.setTitle(getChildTitle("Settings"));
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/icons/stageIcon.png")));

        // Sizing of stage
        stage.sizeToScene();
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.primaryStage);
        this.addCloseRequestEvent(stage);
        Main.settingsStage = stage;
        Main.settingsSceneController = loader.getController();
    }

    /**
     * Adds an event to stage which handles the close
     *
     * @param stage  The stage
     */
    public void addCloseRequestEvent(Stage stage){
        stage.setOnCloseRequest(event -> {
            if(stage == Main.settingsStage){
                // Save settings
                if(Main.settingsSceneController.comboBox.getValue() != null)
                    Main.settings.setLanguage(Main.settingsSceneController.comboBox.getValue());
            }
            else if(stage != Main.primaryStage){

                Main.network.disconnectClient();
                Main.network.closeServer();
                Main.network.networkThreads.forEach(java.lang.Thread::interrupt);
            }
        });
    }

    /**
     * Adds a key event to the chat interface
     */
    public void addChatKeyEvent(){
        Main.chatSceneController.messageField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ENTER){
                // Fire event
                NetworkEventManager.fireEvent(new SendMessageEvent(Main.network.getClientsName()));
            }
        });
    }

    /**
     * A method to go back to the menu
     * It disconnects first from the server and then opens the main sage
     *
     * @param owner This stage will be closed
     */
    public void backToMainMenu(Stage owner){
        owner.close();
        Main.network.disconnectClient();
        Main.network.closeServer();
        Main.primaryStage.show();

        // Reset chat and online list
        Main.chatSceneController.messageField.clear();
        Main.chatSceneController.mainChatWindow.clear();
        Main.chatSceneController.getOnlineUsersArea().clear();

        ConsoleLogger.println(LoggingLevel.TIP, true, "Stage returned to menu.");
    }

    /**
     * Fires the close event to activate the closerequestevent
     *
     * @param stage The stage
     */
    public void fireCloseEvent(Stage stage){
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    // Child title is Main title | Child title
    public String getChildTitle(String childTitle){
        return Main.TITLE + " | " + childTitle;
    }

    public Stage getPrimaryStage(){
        return Main.primaryStage;
    }

}
