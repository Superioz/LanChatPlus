package main.de.superioz.lcp;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.de.superioz.lcp.chat.Chat;
import main.de.superioz.lcp.gui.GUIManager;
import main.de.superioz.lcp.gui.container.StartupInput;
import main.de.superioz.lcp.gui.scenes.ChatSceneController;
import main.de.superioz.lcp.gui.scenes.StartMenuController;
import main.de.superioz.lcp.logging.ConsoleLogger;
import main.de.superioz.lcp.logging.LoggingLevel;
import main.de.superioz.lcp.network.Network;
import main.de.superioz.lcp.network.eventsystem.events.*;
import main.de.superioz.lcp.network.eventsystem.handling.NetworkEventManager;
import main.de.superioz.lcp.network.eventsystem.listener.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Class created on März in 2015
 */
public class Main extends Application {

    public static final String TITLE = "LanChat+";
    public static Network network;
    public static Chat networkChat;
    public static StartupInput startupInput;

    public static GUIManager guiManager;
    public static Stage primaryStage;
    public static Stage chatInterface;
    public static NetworkEventManager eventManager;

    public static ChatSceneController chatSceneController;
    public static StartMenuController primaryStageController;

    /**
     * The main method of the application. First it starts the server if a
     * localhost chat server didn't start yet and then it connects with a client.
     *
     * @param args default java program arguments
     */
    public static void main(String[] args){
        launch(args);
    }

    /**
     * The start method of the application.
     * First it opens the start menu to enter name etc and then the chat
     *
     * @param stage javafx primary stage variable
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception{
        /*
         * Creates GUI Manager who adds objects and styles them
         * & Inits the primary stage as local variable
         */
        primaryStage = stage;

        /*
        Init manager for events and register listener
         */
        eventManager = new NetworkEventManager();
        registerListener();

        /*
         * Startup Menu which shows a stage where you can choose
         * your name and the port. If you click finish you create
         * a server and joins to it or you connect directly to the
         * localhost server with given port
         */
        guiManager = new GUIManager(this);
        primaryStage.setTitle(guiManager.getChildTitle("Startup Menu"));
        guiManager.setupStartInterface();

        /*
         * Resize stage to scene and show it
         */
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/icons/stageIcon.png")));

        // Set Close Reuest Event and show
        this.showStartupMenu();

        // Focus on first Textfield
        primaryStageController.usernameField.requestFocus();

        // Chat
        networkChat = new Chat();

        // Logging to Console
        ConsoleLogger.println(LoggingLevel.TIP, true, "Startup Menu shown.");
    }

    public void showStartupMenu(){
        primaryStage.show();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public String getTitle(){
        return TITLE;
    }

    public GUIManager getGuiManager(){
        return guiManager;
    }

    /**
     * Registers all needed events
     */
    public void registerListener(){
        eventManager.registerListener(LaunchChatEventListener.class, LaunchChatEvent.class);
        eventManager.registerListener(ReceivingPacketEventListener.class, ReceivingPacketEvent.class);
        eventManager.registerListener(SendMessageEventListener.class, SendMessageEvent.class);

        eventManager.registerListener(ClientConnectEventListener.class, ClientConnectEvent.class);
        eventManager.registerListener(ClientDisconnectEventListener.class, ClientDisconnectEvent.class);
    }

    /**
     * Returns the computername of the used computer
     */
    public static String getComputername(){
        return System.getProperty("user.name");
    }

    public static String getAddress(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        }catch(UnknownHostException e){
            e.printStackTrace();
        }

        return "-1";
    }

}
