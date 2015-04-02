package main.de.superioz.lcp.util.settings;

/**
 * Class created on April in 2015
 */
public enum SettingKey {

    LANGUAGE("lang");

    private String key;

    SettingKey(String key){
        this.key = key;
    }

    public String getName(){
        return key;
    }

}
