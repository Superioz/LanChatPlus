package main.de.superioz.lcp.util.connection;

import main.de.superioz.lcp.messaging.cmdsystem.ChatCommand;
import main.de.superioz.lcp.messaging.packet.DataPacket;

/**
 * Class created on März in 2015
 */
public class PacketUtil {

    public static final String STRING_SEPERATOR = "\\$";

    /**
     * Returns a string object which contains informations from the datapacket
     *
     * @param packet Which datapacket should be converted
     * @return The string object
     */
    public static String toString(DataPacket packet){
        String packetString = "";
        String[] content = packet.getContent().toArray();

        for(int i = 0; i < content.length; i++){
            packetString += content[i];

            if(i < content.length-1)
                packetString += STRING_SEPERATOR;
        }

        return packetString;
    }

    /**
     * Returns a string array from converted üacket data
     *
     * @param packetData the packet data which was converted
     * @return The string array object
     */
    public static DataPacket fromString(String packetData){
        String[] inst = packetData.split(STRING_SEPERATOR);

        for(int i = 0; i < inst.length; i++){
            inst[i] = inst[i].replace("\\", "");
        }

        return new DataPacket(inst[0], inst[1], inst[2]);
    }

    /**
     * Returns a data packet object to send to the server instructions
     *
     * @param clientName What is the name of the client
     * @param command What command shoud the server receive
     * @param content What is the content of the command
     * @return The data packet object made out of the args
     */
    public static DataPacket make(String clientName, ChatCommand command, String content){
        return new DataPacket(clientName, command.getCommandLine(), content);
    }

}
