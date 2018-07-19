package Core.Clients;

import Core.Message;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class ClientConnections {

    private List<Socket> clientConnections;

    public ClientConnections(List<Socket> clientConnections) {
        this.clientConnections = clientConnections;
    }

    public void add(Socket socket) {
        clientConnections.add(socket);
    }

    public void broadcast(String message) throws IOException {
        for (Socket clientConnection : clientConnections) {
            new PrintStream(clientConnection.getOutputStream())
                    .println(Message.echoIntro + message);
        }
    }
}
