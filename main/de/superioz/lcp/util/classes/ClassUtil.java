package main.de.superioz.lcp.util.classes;

/**
 * Class created on März in 2015
 */
public class ClassUtil {

    /**
     * Checks if the given class implements the given interface
     *
     * @param clazz Class
     * @param interfaze Implements...what
     * @return If the class implements the interface
     */
    public static boolean containsInterface(Class<?> clazz, Class<?> interfaze){
        for(Class<?> i : clazz.getInterfaces()){
            if(i.equals(interfaze)){
                return true;
            }
        }

        return false;
    }

}
