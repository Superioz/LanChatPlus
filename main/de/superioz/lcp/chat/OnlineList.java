package main.de.superioz.lcp.chat;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

/**
 * Class created on März in 2015
 */
public class OnlineList {

    private TextArea listArea;
    private List<String> users;

    /**
     * A handler to refresh the user online list
     *
     * @param area Which text area in app is the online list
     */
    public OnlineList(TextArea area){
        this.listArea = area;
        this.users = new ArrayList<>();
    }

    /**
     * Adds an user to the list if exist
     *
     * @param name The name of the user
     */
    public void add(String name){
        users.add(name);

        this.refresh();
    }

    /**
     * Removes an user from the list
     *
     * @param name The name of the user
     */
    public void remove(String name){
        if(users.contains(name))
            users.remove(name);

        this.refresh();
    }

    /**
     * Refreshes the userlist
     */
    public void refresh(){
        listArea.clear();

        for(String s : users){
            listArea.appendText(s + "\n");
        }
    }

    /**
     * Clears the online list
     */
    public void clear(){
        this.users.clear();
        this.refresh();
    }

    public TextArea getListArea(){
        return listArea;
    }

    public List<String> getUsers(){
        return users;
    }
}
