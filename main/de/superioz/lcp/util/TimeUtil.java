package main.de.superioz.lcp.util;

import java.time.LocalTime;

/**
 * Class created on März in 2015
 */
public class TimeUtil {

    /**
     * Returns an integer array with following content:
     * [0] = hour
     * [1] = minute
     * [2] = second
     *
     * @return The integer array
     */
    public static int[] getTime(){
        int[] time = new int[3];
        LocalTime timeNow = LocalTime.now();

        // Put into array
        time[0] = timeNow.getHour();
        time[1] = timeNow.getMinute();
        time[2] = timeNow.getSecond();

        return time;
    }

    /**
     * Returns the current time in a string
     *
     * @return The string
     */
    public static String getCurrentTime(){
        int[] now = TimeUtil.getTime();
        String[] nowString = new String[now.length];

        for(int i = 0; i < now.length; i++){
            String s =now[i]+"";

            if(s.length() == 1){
                s = "0"+now[i];
            }

            nowString[i] = s;
        }

        return nowString[0]+":"+nowString[1]+":"+nowString[2];
    }

}
