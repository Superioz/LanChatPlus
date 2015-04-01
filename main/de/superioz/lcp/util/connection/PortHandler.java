package main.de.superioz.lcp.util.connection;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

/**
 * Class created on März in 2015
 *
 * A tool to handle ports
 */
public class PortHandler {

    public static final int DEFAULT_PORT = 55555;
    private static final int MIN_PORT_NUMBER = 0;
    private static final int MAX_PORT_NUMBER = 65535;

    /**
     * Checks if the port is in the right range
     *
     * @param port This port will be checked
     * @return If the port is between min- and maxPort
     */
    public static boolean checkRightnessOf(int port){
        return port < MAX_PORT_NUMBER && port > MIN_PORT_NUMBER;
    }

    /**
     * A method to check if a port is in use.
     * Method origin: http://camel.apache.org/
     * <br>
     * <br>Minimum port number is: <b>49152</b>
     * <br>Maximum port number is: <b>65535</b>
     *
     * @param port This port will be checked
     * @return If the port is available
     */
    public static boolean checkAvailabilityOf(int port){
        if(!checkRightnessOf(port)){
            throw new IllegalArgumentException("Invalid start port: " + port);
        }

        ServerSocket ss = null;
        DatagramSocket ds = null;

        try{
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        }catch(IOException ignored){
        }finally{
            if(ds != null){
                ds.close();
            }

            if(ss != null){
                try{
                    ss.close();
                }catch(IOException e){/* should not be thrown */}
            }
        }

        return false;
    }

}
