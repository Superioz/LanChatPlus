package main.de.superioz.lcp.network.actions;

/**
 * Class created on März in 2015
 */
public class NetInstruction {

    private String clientsName;
    private String commandLine;
    private String commandContent;

    public NetInstruction(String clientsName, String commandLine, String commandContent){
        this.clientsName = clientsName;
        this.commandContent = commandContent;
        this.commandLine = commandLine;
    }

    public String getCommandContent(){
        return commandContent;
    }

    public String getClientsName(){
        return clientsName;
    }

    public String getCommandLine(){
        return commandLine;
    }


    @Override
    public String toString(){
        return "NetInstruction{" +
                "clientsName='" + clientsName + '\'' +
                ", commandLine='" + commandLine + '\'' +
                ", commandContent='" + commandContent + '\'' +
                '}';
    }

    public String[] toArray(){
        return new String[]{clientsName,commandLine,commandContent};
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof NetInstruction)) return false;

        NetInstruction that = (NetInstruction) o;

        return getClientsName().equals(that.getClientsName())
                && getCommandLine().equals(that.getCommandLine())
                && getCommandContent().equals(that.getCommandContent());

    }

    @Override
    public int hashCode(){
        int result = getClientsName().hashCode();
        result = 31 * result + getCommandLine().hashCode();
        result = 31 * result + getCommandContent().hashCode();
        return result;
    }
}
