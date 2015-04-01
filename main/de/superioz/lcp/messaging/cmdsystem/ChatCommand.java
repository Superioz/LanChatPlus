package main.de.superioz.lcp.messaging.cmdsystem;

/**
 * Class created on März in 2015
 */
public enum ChatCommand {

    /*
     Commands to send instruction to server who sends it to all clients
     A message must send to server first and then print to chat when a client receives it
      */
    BROADCAST("broadcast", CommandType.CLIENT_TO_SERVER),
    WELCOME("welcome", CommandType.CLIENT_TO_SERVER),
    GOODBYE("goodbye", CommandType.CLIENT_TO_SERVER),
    PRINT_TO_CHAT("print", CommandType.SERVER_TO_CLIENT),
    USER_JOINED("user_joined", CommandType.SERVER_TO_CLIENT),
    NEW_ONLINE_LIST("new_onlinelist", CommandType.CLIENT_TO_SERVER),
    USER_LEFT("user_left", CommandType.SERVER_TO_CLIENT);

    public String commandLine;
    public CommandType type;

    ChatCommand(String commandLine, CommandType type){
        this.commandLine = commandLine;
        this.type = type;
    }

    public String getCommandLine(){
        return commandLine;
    }

    public CommandType getType(){
        return type;
    }

    public static ChatCommand getFromLine(String commandLine){
        for(ChatCommand chatCommand : ChatCommand.values()){
            if(chatCommand.getCommandLine().equalsIgnoreCase(commandLine)){
                return chatCommand;
            }
        }

        return null;
    }

}
