package main.de.superioz.lcp.network.connection;

import main.de.superioz.lcp.util.connection.PortHandler;

/**
 * Class created on März in 2015
 */
public class Port {

    private int port;

    public Port(int portValue){
        if(PortHandler.checkRightnessOf(portValue)){
            port = portValue;
        }
        else{
            port = PortHandler.DEFAULT_PORT;
        }
    }

    /**
     * Returns this port in an integer
     *
     * @return The integervalue of this port
     */
    public int getValue(){
        return port;
    }

    /**
     * Checks the availability of this port
     *
     * @return If this port is available or not
     */
    public boolean check(){
        return PortHandler.checkAvailabilityOf(port);
    }

    @Override
    public int hashCode(){
        return port;
    }

    @Override
    public String toString(){
        return "Port:"+port;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Port){
            Port otherIP = (Port) object;

            return otherIP.getValue()==this.getValue();
        }

        return false;
    }

}
