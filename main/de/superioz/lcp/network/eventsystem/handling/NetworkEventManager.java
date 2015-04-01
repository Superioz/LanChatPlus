package main.de.superioz.lcp.network.eventsystem.handling;

import main.de.superioz.lcp.network.Network;
import main.de.superioz.lcp.network.eventsystem.NetworkEvent;
import main.de.superioz.lcp.network.eventsystem.NetworkEventListener;
import main.de.superioz.lcp.util.classes.ClassUtil;
import main.de.superioz.lcp.util.classes.ReflectionUtil;

import java.util.HashMap;

/**
 * Class created on März in 2015
 */
public class NetworkEventManager {

    private static HashMap<Class<? extends NetworkEvent>, Class> listener;

    /**
     * The manager is used to register/unregister listener and to throw events
     *
     * @param network The network for the manager++
     */
    public NetworkEventManager(){
        listener = new HashMap<>();
    }

    /**
     * Adds a listener class to the network listener map
     *
     * @param listenerClass The class which listens to the event
     * @param eventClass The event
     * @return If the class implements listener and if it's put onto the map
     */
    public boolean registerListener(Class listenerClass, Class<? extends NetworkEvent> eventClass){
        if(ClassUtil.containsInterface(listenerClass, NetworkEventListener.class)){
            listener.put(eventClass, listenerClass);
            return true;
        }

        return false;
    }

    /**
     * Unregisters a listener from list
     *
     * @param listenerClass The listener class
     * @return If it was successful
     */
    public boolean unregisterListener(Class listenerClass, Class<? extends NetworkEvent> eventClass){
        if(listener.get(eventClass) == listenerClass){
            listener.remove(eventClass);

            return true;
        }

        return false;
    }

    /**
     * Throws an event to handle it better
     *
     * @param event The thrown event
     * @return If the 'throw' was successful
     */
    public static boolean fireEvent(NetworkEvent event){
        /*
        Get all listener classes and adds them to the map
        if they suit to the pattern
         */
        listener.keySet().stream().filter(l -> l == event.getClass()).forEach(l -> {
            Class listenerClass = listener.get(l);

            // Loop through all methods with the event param
            // 1 If the method only have the event param, then...
            // 2 If the method has got the annotation 'event handler', then...
            // 3 Check if the method returns void
            ReflectionUtil.getMethodsWith(listenerClass, l).stream().filter(m
                    -> ReflectionUtil.getParamLength(m) == 1
                    && m.isAnnotationPresent(NetEventHandler.class)
                    && m.getReturnType().equals(Void.TYPE)).forEach(m -> {

                try{
                    // Invoke event and run method
                    m.invoke(listenerClass.newInstance(), event);
                }catch(Exception e){
                    e.printStackTrace();
                }
            });
        });

        return false;
    }


    protected HashMap<Class<? extends NetworkEvent>, Class> getListener(){
        return listener;
    }

}
