package main.de.superioz.lcp.logging;

/**
 * Class created on März in 2015
 */
public enum LoggingLevel {

    /**
     * Great, you did it!
     */
    SUCCESS(LoggingColor.GREEN),

    /**
     * It's all okay.
     */
    LOW(LoggingColor.WHITE),

    /**
     * Did you know that ... ?!
     */
    TIP(LoggingColor.CYAN),

    /**
     * Oh, watch out!
     */
    WARNING(LoggingColor.YELLOW),

    /**
     * Just an info
     */
    INFORMATION(LoggingColor.MAGENTA),

    /**
     * Oops, there is an error!
     */
    ERROR(LoggingColor.RED);

    // The color for the message
    private LoggingColor color;

    LoggingLevel(LoggingColor loggingColor){
        this.color = loggingColor;
    }

    /**
     * Returns the message color of the level
     *
     * @return The color object of the level
     */
    public LoggingColor getColor(){
        return this.color;
    }

}
