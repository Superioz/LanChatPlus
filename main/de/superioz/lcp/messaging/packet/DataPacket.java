package main.de.superioz.lcp.messaging.packet;

import main.de.superioz.lcp.network.actions.NetInstruction;
import main.de.superioz.lcp.util.connection.PacketUtil;

import java.util.Arrays;

/**
 * Class created on März in 2015
 */
public class DataPacket {

    /**
     * Content of the packet
     */
    private NetInstruction content;

    /**
     * A packet is used to send data from client to server and vice versa
     *
     * @param content The content of the stream will be saved here in content
     */
    public DataPacket(String clientsName, String commandLine, String commandContent){
        this.content = new NetInstruction(clientsName,commandLine,commandContent);
    }

    public NetInstruction getContent(){
        return content;
    }

    @Override
    public int hashCode(){
        return content.hashCode();
    }

    @Override
    public String toString(){
        return PacketUtil.toString(this);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof DataPacket){
            DataPacket p = (DataPacket)obj;

            if(p.getContent().equals(this.getContent())){
                return true;
            }
        }

        return false;
    }

}
