package main.de.superioz.lcp.util.classes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class created on März in 2015
 */
public class ReflectionUtil {

    /**
     * Returns a method from a class with the given parameters
     *
     * @param c The class
     * @param parameters Which parameters should be checked
     * @return The found method
     */
    public static List<Method> getMethodsWith(Class<?> c, Class<?>... parameters){
        List<Class<?>> params = Arrays.asList(parameters);
        List<Method> methods = new ArrayList<>();

        for(Method m : c.getMethods()){
            for(Class<?> p : m.getParameterTypes()){
                if(params.contains(p))
                    methods.add(m);
            }
        }

        return methods;
    }

    /**
     * Returs the length of the parameter array from the method
     *
     * @param m Checked method
     * @return Number of parameters
     */
    public static int getParamLength(Method m){
        return m.getParameterTypes().length;
    }

}
