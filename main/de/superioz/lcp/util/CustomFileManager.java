package main.de.superioz.lcp.util;

import main.de.superioz.lcp.Main;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

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
        return getInformationFromFile("/main/resources/textfiles/" + name);
    }

    public static String getJarPath(){
        try{
            String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            return URLDecoder.decode(path, "UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return null;
    }

    public static File createFile(boolean ifNotExists, String fileName, String path){
        String filePath = path + fileName;
        File f = new File(filePath);

        if(f.exists()){
            return f;
        }
        else if((ifNotExists && !f.exists()) || !ifNotExists){
            try{
                if(f.createNewFile()){
                    return f;
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    public static File createPropertiesFile(String name, String path, String text){
        File file = new File(path + name);
        boolean b = file.exists();

        if(!b){
            file = createFile(true, name, path);
            writeToFile(file, text);
        }

        return file;
    }

    public static void writeToFile(File f, String text){
        try(Writer writer
                    = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8))){
            writer.write(text);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
