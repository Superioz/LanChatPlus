package main.de.superioz.lcp.gui.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.gui.container.StartupInput;
import main.de.superioz.lcp.logging.ConsoleLogger;
import main.de.superioz.lcp.logging.LoggingLevel;
import main.de.superioz.lcp.network.eventsystem.events.LaunchChatEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetworkEventManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class created on März in 2015
 */
public class StartMenuController implements Initializable{

    // Variables of Scene
    @FXML public Text header;

    @FXML public Label enterName;
    @FXML public Label enterPort;

    @FXML public TextField usernameField;
    @FXML public TextField addressField;
    @FXML public TextField portField;

    @FXML public Button exit;
    @FXML public Button launch;

    @FXML public Menu program;
    @FXML public MenuItem exitMenu;
    @FXML public MenuItem settingsMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.init();
    }

    /**
     * Inits the stages for updated language or start
     */
    public void init(){
        enterName.setText(Main.lang.get("startupMenuEnterName"));
        enterPort.setText(Main.lang.get("startupMenuEnterPort"));

        usernameField.setPromptText(Main.lang.get("startupMenuNameField"));
        addressField.setPromptText(Main.lang.get("startupMenuAddressField"));
        portField.setPromptText(Main.lang.get("startupMenuPortField"));

        exit.setText(Main.lang.get("startupMenuExitButton"));
        launch.setText(Main.lang.get("startupMenuLaunchButton"));

        program.setText(Main.lang.get("startupMenuProgramMenu"));
        exitMenu.setText(Main.lang.get("startupMenuExitMenuitem"));
        settingsMenu.setText(Main.lang.get("startupMenuSettingsMenuitem"));
    }

    @FXML
    public void handleExitButton(ActionEvent event){
        Main.guiManager.fireCloseEvent(Main.primaryStage);
    }

    @FXML
    public void handleLaunchButton(ActionEvent event){
        String username = usernameField.getText();
        String port = portField.getText();

        StartupInput startupInput = new StartupInput(username, port, addressField.getText());

        if(Main.startupInput == null){
            Main.startupInput = startupInput;
        }

        // Start Thread with starting chat gui and server
        ConsoleLogger.println(LoggingLevel.TIP, true, "Launching chat ..");

        NetworkEventManager.fireEvent(new LaunchChatEvent(startupInput));
    }

    @FXML
    public void handleExit(ActionEvent event){
        Main.guiManager.fireCloseEvent(Main.primaryStage);
    }

    @FXML
    public void handleSettings(ActionEvent event){
        Main.settingsStage.show();
    }
}
