package main.de.superioz.lcp.logging;

import main.de.superioz.lcp.util.TimeUtil;

/**
 * Class created on März in 2015
 */
public class ConsoleLogger {

    /**
     * Prints a message/ messages to the console with color (depends on level)
     *
     * @param loggingLevel This level sets the message color
     * @param msg This message will be send
     */
    public static void print(LoggingLevel loggingLevel, String... msg){
        for(String s : msg){
            String coloredMessage = loggingLevel.getColor().applyPattern(s);
            System.out.print(coloredMessage);
        }
    }

    public static void print(LoggingLevel loggingLevel, boolean withTime, String... msg){
        for(String s : msg){
            String coloredMessage = loggingLevel.getColor()
                    .applyPattern((withTime ? "["+TimeUtil.getCurrentTime()+"] " : "") + s);
            System.out.print(coloredMessage);
        }
    }

    /**
     * Prints a message/ messages to the console with color (depends on level) and a tabulator
     *
     * @param loggingLevel This level sets the message color
     * @param msg This message will be send
     */
    public static void println(LoggingLevel loggingLevel, String... msg){
        for(int i = 0; i < msg.length; i++){
            msg[i] += "\n";
        }

        print(loggingLevel, msg);
    }

    public static void println(LoggingLevel loggingLevel, boolean withTime, String... msg){

        for(int i = 0; i < msg.length; i++){
            msg[i] = msg[i] + "\n";
        }

        print(loggingLevel, withTime, msg);
    }

}
