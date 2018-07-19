package Core.Mains;

import Core.Server.Server;
import Core.Server.ServerStatus;

import java.io.*;
import java.net.ServerSocket;

public class MainServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        Server server = new Server(System.out, serverSocket, new ServerStatus());
        server.start();
    }
}
