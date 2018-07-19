package Core.Server;

import Core.Clients.ClientConnections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EachClientConnectionThread implements Runnable {

    private ClientConnections clientConnections;
    private BufferedReader reader;

    public EachClientConnectionThread(ClientConnections clientConnections, Socket clientConnection) throws IOException {
        this.clientConnections = clientConnections;
        reader = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
    }

    public void run() {
        try {
            listenAndBroadcast();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenAndBroadcast() throws IOException {
        String message;
        while ((message = reader.readLine()) != null) {
            clientConnections.broadcast(message);
        }
    }
}
