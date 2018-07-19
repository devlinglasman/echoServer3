package Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerListener implements Runnable {

    private Server server;
    private BufferedReader reader;

    public ServerListener(Server server, Socket socket) throws IOException {
        this.server = server;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
//        try {
//            broadcastMessages();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

//    public void broadcastMessages() throws IOException {
//        String message;
//        while ((message = reader.readLine()) != null) {
//            server.broadcastMessage(message);
//        }
//    }
}
