package Core.Server;

import Core.Clients.ClientConnections;
import Core.Message;

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
                Socket clientConnection = serverSocket.accept();
                clientConnections.add(clientConnection);

                stdOut.println(Message.clientConnected());

                EachClientConnectionThread EachClientConnectionThread =
                        new EachClientConnectionThread(clientConnections, clientConnection);
                new Thread(EachClientConnectionThread).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientConnections getClientConnections() {
        return clientConnections;
    }


}
