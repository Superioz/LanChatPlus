package main.de.superioz.lcp.lang;

import java.util.Locale;

/**
 * Class created on April in 2015
 */
public enum Language {

    ENGLISH(Locale.ENGLISH),
    GERMAN(Locale.GERMAN);

    private Locale locale;

    Language(Locale locale){
        this.locale = locale;
    }

    public Locale getLocale(){
        return locale;
    }

    public static Language fromString(String name){
        Language lang;

        switch(name.toLowerCase()){
            case "deutsch":
                lang = Language.GERMAN;
                break;
            case "english":
                lang = Language.ENGLISH;
                break;
            default:
                lang = Language.ENGLISH;
                break;
        }

        return lang;
    }

    @Override
    public String toString(){
        return locale.getLanguage();
    }

}
