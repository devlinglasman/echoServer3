package Core.Servers;

import Core.Clients.ClientConnections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientMessageBroadcaster implements Runnable {

    private ClientConnections clientConnections;
    private BufferedReader reader;

    public ClientMessageBroadcaster(ClientConnections clientConnections, Socket clientConnection) throws IOException {
        this.clientConnections = clientConnections;
        reader = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
    }

    public void run() {
        try {
            while (true) {
                execute(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void execute(String message) {
            broadcast(message);
    }

    private void broadcast(String message) {
        try {
            clientConnections.broadcast(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
