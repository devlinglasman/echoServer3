package Core;

import Core.Clients.ClientConnections;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private PrintStream stdOut;
    private ServerSocket serverSocket;
    private ClientConnections clientConnections;
    private ServerStatus serverStatus;

    public Server(PrintStream stdOut, ServerSocket serverSocket, ServerStatus serverStatus) {
        this.serverSocket = serverSocket;
        this.stdOut = stdOut;
        this.serverStatus = serverStatus;
        clientConnections = new ClientConnections(new ArrayList<>());
        start();
    }

    public void start() {
        try {
            while (serverStatus.isRunning()) {
                Socket clientSocket = serverSocket.accept();
                stdOut.println(Message.clientConnected());

                ServerListener serverListener = new ServerListener(this, clientSocket);
                new Thread(serverListener).start();
//                clientConnections.add(clientSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
