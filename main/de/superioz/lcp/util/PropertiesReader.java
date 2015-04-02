package main.de.superioz.lcp.util;

import java.io.*;
import java.util.Properties;

/**
 * Class created on April in 2015
 */
public class PropertiesReader {

    private Properties prop;
    public File propFile;
    public String fileName;
    public String filePath;

    /**
     * Reads from a properties file and gives a method where you can get a map from it
     *
     * @param fileName Name of the .properties File
     * @param filePath Path where the file is located
     */
    public PropertiesReader(String fileName, String filePath, boolean init){
        this.fileName = fileName;
        this.filePath = filePath;

        if(init){
            this.initFile();
        }

        prop = new Properties();
        if(init){
            try(FileInputStream inputStream = new FileInputStream(this.propFile)){
                prop.load(inputStream);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                InputStream stream = getClass().getResourceAsStream(filePath + fileName + ".properties");
                prop.load(stream);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void initFile(){
        this.propFile = CustomFileManager.createPropertiesFile("settings"
                , CustomFileManager.getJarPath(), CustomFileManager.getTextFile("config"));
    }

    /**
     * Returns the map of the given key
     *
     * @param key The key (left side from value in .properties)
     * @return The key object in string form
     */
    public String getProperty(String key){
        return prop.getProperty(key);
    }

    /**
     * Sets the value of given key in .properties file
     *
     * @param key The key of the value
     * @param value The new value
     */
    public void setProperty(String key, String value){
        try{
            prop.setProperty(key, value);
            OutputStream out = new FileOutputStream(this.propFile);
            prop.store(out, "Settings saved!");
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
