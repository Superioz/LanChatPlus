package main.de.superioz.lcp.util.settings;

import main.de.superioz.lcp.util.CustomFileManager;
import main.de.superioz.lcp.util.PropertiesReader;

/**
 * Class created on April in 2015
 */
public class SettingsManager {

    private String language;
    private PropertiesReader propReader;

    public void init(){
        propReader = new PropertiesReader("settings", CustomFileManager.getJarPath(), true);

        // Settings
        this.language = propReader.getProperty(SettingKey.LANGUAGE.getName());
    }

    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language){
        this.propReader.setProperty(SettingKey.LANGUAGE.getName(), language);
        this.language = language;
    }

}
