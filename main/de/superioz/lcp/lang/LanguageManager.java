package main.de.superioz.lcp.lang;

import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.util.PropertiesReader;

/**
 * Class created on April in 2015
 */
public class LanguageManager {

    // Property files
    private PropertiesReader props;
    private Language language;

    public LanguageManager(Language lang){
        this.language = lang;
        this.init();
    }

    /**
     * Inits all readers. Creates for every existing language a properties reader
     */
    public void init(){
        props = getLanguagePropReader(this.language);
    }

    /**
     * Returns a properties reader of the properties file with lang given
     *
     * @param lang The language
     * @return The properties reader object
     */
    public PropertiesReader getLanguagePropReader(Language lang){
        String name = lang.toString() + "_lang";

        return new PropertiesReader(name, "/main/resources/lang/", false);
    }

    public String get(String key){
        return props.getProperty(key);
    }

    public void refreshLang(){
        this.language = Language.fromString(Main.settings.getLanguage());
        this.init();
    }

}
