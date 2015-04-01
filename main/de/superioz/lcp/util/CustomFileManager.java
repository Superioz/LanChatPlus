package main.de.superioz.lcp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class created on März in 2015
 */
public class CustomFileManager {

    /**
     * Returns a string from a .txt file
     *
     * @param filePath The file path
     * @return The string object
     */
    public static String getInformationFromFile(String filePath){
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(CustomFileManager.class.getResourceAsStream(filePath + ".txt")))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            return sb.toString();
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getTextFile(String name){
        return getInformationFromFile("/main/ressources/textfiles/" + name);
    }

}
