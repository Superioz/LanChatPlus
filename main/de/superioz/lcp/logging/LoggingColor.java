package main.de.superioz.lcp.logging;

/**
 * Class created on März in 2015
 */
public enum LoggingColor {

    BLACK(30),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    MAGENTA(35),
    CYAN(36),
    WHITE(37);

    private static final String PATTERN = (char)27 + "[%c" + "m" + "%msg";
    private static final String RESETTER = (char)27 + "[0m";
    public int ansiCode;

    LoggingColor(int ansiCode){
        this.ansiCode = ansiCode;
    }

    /**
     * Applies the pattern to the given string
     *
     * @param msg The pattern will be apply to this string
     * @return The string with new color depending on the ansi code above
     */
    public String applyPattern(String msg){
        return PATTERN.replaceAll("%msg", msg).replaceAll("%c", this.ansiCode+"") + RESETTER;
    }

}
