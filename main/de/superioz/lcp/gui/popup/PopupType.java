package main.de.superioz.lcp.gui.popup;

import javafx.scene.control.Alert;

/**
 * Class created on März in 2015
 */
public enum PopupType {

    ERROR(Alert.AlertType.ERROR),
    WARNING(Alert.AlertType.WARNING),
    INFORMATION(Alert.AlertType.INFORMATION);

    Alert.AlertType type;

    PopupType(Alert.AlertType type){
        this.type = type;
    }

    public Alert.AlertType getType(){
        return type;
    }
}
