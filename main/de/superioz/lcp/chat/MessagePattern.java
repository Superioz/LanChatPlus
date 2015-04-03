package main.de.superioz.lcp.chat;

import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.util.TimeUtil;

/**
 * Class created on März in 2015
 */
public enum MessagePattern {

    USER("[%time%] %client%: %message%"),
    SERVER("[%time%] *** %message%"),
    JOIN_AND_LEAVE("[%time%] >> %message%"),
    PRIVATE_SENDER("[%time%] [" + Main.lang.get("chatPrivateTo") + " %sender%] » %message%"),
    PRIVATE_RECEIVER("[%time%] [" + Main.lang.get("chatPrivateFrom") + " %sender%] » %message%");

    public String pattern;

    MessagePattern(String pattern){
        this.pattern = pattern;
    }

    public String getPattern(){
        return pattern;
    }

    /**
     * Applies a pattern on a string
     *
     * @param message The string where the pattern will be applied
     * @param pattern The pattern object for a message
     */
    public static String applyPatternOn(String message, String clientName, MessagePattern pattern){
        String applied = pattern.getPattern();

        return applied
                .replaceAll("%time%", TimeUtil.getCurrentTime())
                .replaceAll("%client%", clientName)
                .replaceAll("%message%", message);
    }

    public static String applyPatternOn(String message, MessagePattern pattern, String sender){
        String applied = pattern.getPattern();

        return applied
                .replaceAll("%time%", TimeUtil.getCurrentTime())
                .replaceAll("%sender%", sender)
                .replaceAll("%message%", message);
    }

}
