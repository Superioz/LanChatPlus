package main.de.superioz.lcp.network.connection;

/**
 * Class created on März in 2015
 */
public class IP {

    private String ip;

    public IP(String ipValue){
        this.ip = ipValue.replaceAll(" ", "");
    }

    /**
     * Returns this port in a string
     *
     * @return The string of this port
     */
    public String getValue(){
        return ip;
    }

    @Override
    public int hashCode(){
        return ip.hashCode();
    }

    @Override
    public String toString(){
        return "IP:" + ip;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof IP){
            IP otherIP = (IP) object;

            return otherIP.getValue().equals(this.getValue());
        }

        return false;
    }

}
