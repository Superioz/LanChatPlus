# LanChatPlus
A just for education written Chat program, where you can chat with others in Lan.

## Prolog
This program is just an exercise to test stuff with sockets, streams and so on, so if it doesn't work with older Java versions then Java 8 or if it doesn't start with Windows 7 then please don't create a new issue here. **Reasons for an issue report** are following:
- If it doesn't work with **Java 8 and Windows 8**
- If there is a big error which shouldn't appear
- If there is something in the code which could/must be better
- If the code is okay but it doesn't do what it should do
So: I'm not liable, if it doesn't work with Windows 7- or Java 7 (and older versions), because it's written with Windows 8 and Java 8.

## Usage
It's pretty simple to use this chat program. First it opens a window, where you can type in your username, the server port and if needed the server ip. When you click 'Launch' it opens the main chat interface and then should be connected to a server if your input was right.

## How it works
The first user starts a server with the given port and his or her ip address, if no server in network runs on that port. The second user don't start a server if a server already runs on that port with given ip, but he connect to that server. Now there is a connection between the clients and the server. If now one client writes a message, it will be send to the server and the server sends a respond to every client back.

## Attribution
Icons used in this program are made by [Freepik](http://www.freepik.com/) from http://www.flaticon.com and friendly assistance did i get from https://twitter.com/SlickzTV <3  

