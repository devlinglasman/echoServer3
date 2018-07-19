package Core.Servers;

import Core.Clients.ClientConnections;
import Core.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executor;

public class Server {

    private PrintStream stdOut;
    private ServerSocket serverSocket;
    private ClientConnections clientConnections;
    private ServerStatus serverStatus;
    private Executor executor;

    public Server(PrintStream stdOut, ServerSocket serverSocket, ServerStatus serverStatus, Executor executor) {
        this.serverSocket = serverSocket;
        this.stdOut = stdOut;
        this.serverStatus = serverStatus;
        this.executor = executor;
        clientConnections = new ClientConnections(new ArrayList<>());
        start();
    }

    public void start() {
        try {
            while (serverStatus.isRunning()) {
                Socket clientConnection = serverSocket.accept();
                clientConnections.add(clientConnection);

                stdOut.println(Message.clientConnected());

                executor.execute(new EachClientConnectionThread(clientConnections, clientConnection));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
