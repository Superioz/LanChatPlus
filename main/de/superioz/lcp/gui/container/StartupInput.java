package main.de.superioz.lcp.gui.container;

import main.de.superioz.lcp.Main;
import main.de.superioz.lcp.network.connection.Port;
import main.de.superioz.lcp.util.connection.IPHandler;
import main.de.superioz.lcp.util.connection.PortHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class created on März in 2015
 */
public class StartupInput {

    private String name;
    private Port port;
    private String ip;

    /**
     * Puts the entered name and port into an object
     *
     * @param name Entered username
     * @param port Entered port
     */
    public StartupInput(String name, String port, String ip){
        this.verify(name, port, ip);
    }

    /**
     * Verifies the clientname and the port if they suit to the pattern
     *
     * @param name Entered clientname
     * @param port Entered serverport
     */
    private void verify(String name, String port, String ip){
        Pattern pattern = Pattern.compile("[a-zA-Z_0-9]*");
        Matcher matcher = pattern.matcher(name);
        boolean verifiedName = matcher.matches() && name.length() <= 25 && !name.isEmpty();
        this.name = verifiedName ? name : Main.getComputername();

        try {
            int portNumber = Integer.parseInt(port);
            this.port = new Port(portNumber);
        }catch(NumberFormatException ex){
            this.port = new Port(PortHandler.DEFAULT_PORT);
        }

        if(IPHandler.isIP(ip)){
            this.ip = ip;
        }
        else{
            this.ip = Main.getAddress();
        }
    }

    @Override
    public String toString(){
        return "Input: " + name + ";" + port.getValue() + ";" + ip;
    }

    public String getName(){
        return name;
    }

    public Port getPort(){
        return port;
    }

    public String getIp(){
        return ip;
    }
}
