package Core.Mains;

import Core.Clients.Client;
import Core.Servers.Server;
import Core.Servers.ServerStatus;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class MainServer {

    private static int portNumber = 6666;
    private static String host = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        String mode = args[0];

        if (mode.equals("server")) {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Server server = new Server(System.out, serverSocket, new ServerStatus(), Executors.newCachedThreadPool());
            System.out.println("started");
            server.start();
        } else {
            Socket socket = new Socket(host, portNumber);
            Client client = new Client(System.in, System.out, socket, Executors.newCachedThreadPool());
            client.start();
        }
    }
}
