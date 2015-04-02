package main.de.superioz.lcp.gui.popup;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Pair;
import main.de.superioz.lcp.Main;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

/**
 * Class created on März in 2015
 */
public class PopupManager {

    /**
     * Creates a simple popup with custom text and shows it
     *
     * @param type The alerttype of the dialog
     * @param title The window title
     * @param header The header above the content text
     * @param content The content
     */
    public static void popup(Stage owner, PopupType type, String title, String header, boolean exitOnOkay, String... content){
        Alert popupWindow = new Alert(type.getType());
        popupWindow.setTitle(title);
        popupWindow.setHeaderText(header);

        // Context
        String fullContext = "";
        for(String s : content){
            fullContext += s+"\n";
        }
        popupWindow.setContentText(fullContext);

        // Buttons
        ButtonType buttonTypeAccept = new ButtonType("Okay");
        popupWindow.getButtonTypes().setAll(buttonTypeAccept);

        // Style
        popupWindow.getDialogPane().getStylesheets()
                .add(PopupManager.class.getResource("/main/resources/style.css").toExternalForm());

        popupWindow.initOwner(owner);
        Optional<ButtonType> result = popupWindow.showAndWait();
        if(result.get() != null){
            // He accept the error go back to menu
            System.exit(0);

        }
    }

    /**
     * Creates a popup window with exception and a stacktrace inside if needed
     *
     * @param errorText Which error happens
     * @param ex The stacktrace if it was an exception
     */
    public static void popupError(String errorText, String header, Exception ex, Stage owner){
        Alert popupWindow = new Alert(Alert.AlertType.ERROR);
        popupWindow.setTitle(Main.guiManager.getChildTitle(Main.lang.get("popupErrorTitle")));
        popupWindow.setHeaderText(header.isEmpty() ? Main.lang.get("popupErrorHeader") : header);
        popupWindow.setContentText(errorText);

        //Check if exception != null
        if(ex != null){
            // Create area for stacktrace
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            // Text area with header
            Label stacktraceHeader = new Label(Main.lang.get("popupExStacktraceWas"));
            TextArea stacktraceArea = new TextArea(exceptionText);
            stacktraceArea.setEditable(false);
            stacktraceArea.setWrapText(true);
            stacktraceArea.setMaxWidth(Double.MAX_VALUE);
            stacktraceArea.setMaxHeight(Double.MAX_VALUE);

            // Grid pane
            GridPane.setVgrow(stacktraceArea, Priority.ALWAYS);
            GridPane.setHgrow(stacktraceArea, Priority.ALWAYS);
            GridPane gridPane = new GridPane();
            gridPane.setMaxWidth(Double.MAX_VALUE);
            gridPane.add(stacktraceHeader, 0, 0);
            gridPane.add(stacktraceArea, 0, 1);

            // Add to dialog and show
            popupWindow.getDialogPane().setExpandableContent(gridPane);
        }

        // Buttons
        ButtonType buttonTypeAccept = new ButtonType(Main.lang.get("popupUnderstandButton"));
        ButtonType buttonTypeExit = new ButtonType(Main.lang.get("popupExitButton"));
        popupWindow.getButtonTypes().setAll(buttonTypeAccept, buttonTypeExit);

        // Style
        popupWindow.getDialogPane().getStylesheets()
                .add(PopupManager.class.getResource("/main/resources/style.css").toExternalForm());

        // Opens and waits for interaction
        popupWindow.initOwner(owner);
        Optional<ButtonType> result = popupWindow.showAndWait();
        if(result.get() == buttonTypeAccept){
            // He accept the error go back to menu
            Main.guiManager.backToMainMenu(owner);

        }
        else{
            // User exits or closed the window
            Main.guiManager.fireCloseEvent(owner);
        }
    }

    /**
     * Creates a popup with custom graphic and text and shows it
     *
     * @param graphicUrl The URl of the graphic
     * @param title The window title
     * @param header The text header above content
     * @param content The text content
     */
    public static void popupCustom(Stage owner, String graphicUrl, String title, String header, String... content){
        Dialog<Pair<String, String>> popupWindow = new Dialog<>();
        popupWindow.setTitle(Main.guiManager.getChildTitle(title));
        popupWindow.setHeaderText(header);
        popupWindow.setGraphic(new ImageView(graphicUrl));

        // Context
        String fullContext = "";
        for(String s : content){
            fullContext += s+"\n";
        }
        popupWindow.setContentText(fullContext);

        // Buttons
        ButtonType buttonTypeAccept = new ButtonType(Main.lang.get("popupDoneButton"), ButtonBar.ButtonData.OK_DONE);
        popupWindow.getDialogPane().getButtonTypes().setAll(buttonTypeAccept);

        // Style
        popupWindow.getDialogPane().getStylesheets()
                .add(PopupManager.class.getResource("/main/resources/style.css").toExternalForm());

        // Show
        popupWindow.initOwner(owner);
        popupWindow.showAndWait();
    }

}
