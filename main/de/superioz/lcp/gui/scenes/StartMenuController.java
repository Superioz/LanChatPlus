package main.de.superioz.lcp.gui.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.gui.container.StartupInput;
import main.de.superioz.lcp.logging.ConsoleLogger;
import main.de.superioz.lcp.logging.LoggingLevel;
import main.de.superioz.lcp.network.eventsystem.events.LaunchChatEvent;
import main.de.superioz.lcp.network.eventsystem.handling.NetworkEventManager;

/**
 * Class created on März in 2015
 */
public class StartMenuController {

    // Variables of Scene
    @FXML public Text header;

    @FXML public Label enterName;
    @FXML public Label enterPort;

    @FXML public TextField usernameField;
    @FXML public TextField portField;

    @FXML public Button exit;
    @FXML public Button launch;

    @FXML
    public void handleExitButton(ActionEvent event){
        Main.guiManager.fireCloseEvent(Main.primaryStage);
    }

    @FXML
    public void handleLaunchButton(ActionEvent event){
        String username = usernameField.getText();
        String port = portField.getText();

        StartupInput startupInput = new StartupInput(username, port);

        if(Main.startupInput == null){
            Main.startupInput = startupInput;
        }

        // Start Thread with starting chat gui and server
        ConsoleLogger.println(LoggingLevel.TIP, true, "Launching chat ..");

        NetworkEventManager.fireEvent(new LaunchChatEvent(startupInput));
    }

}
