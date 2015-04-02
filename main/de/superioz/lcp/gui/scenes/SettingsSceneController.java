package main.de.superioz.lcp.gui.scenes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import main.de.superioz.lcp.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class created on April in 2015
 */
public class SettingsSceneController implements Initializable {

    @FXML public ComboBox<String> comboBox;
    @FXML public Label languageLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        comboBox.getItems().clear();
        comboBox.getItems().addAll("Deutsch", "English");

        this.init();
    }

    /**
     * Inits the stage
     */
    public void init(){
        comboBox.setValue(Main.settings.getLanguage());

        languageLabel.setText(Main.lang.get("settingsLangLabel"));
    }

}
