package Core.Mains;

import Core.Servers.Server;
import Core.Servers.ServerStatus;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class MainServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        Server server = new Server(System.out, serverSocket, new ServerStatus(), Executors.newCachedThreadPool());
        server.start();
    }
}
