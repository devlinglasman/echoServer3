package Core;

import Core.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private PrintStream stdOut;
    private ServerSocket serverSocket;
    private List<Socket> clients;

    public Server(PrintStream stdOut, ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.stdOut = stdOut;
        clients = new ArrayList<>();
    }

    public void start() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                stdOut.println(Message.clientConnected());

                ServerListener serverListener = new ServerListener(this, clientSocket);
                new Thread(serverListener).start();
                clients.add(clientSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String message) throws IOException {
        for (Socket clientSocket : clients) {
            new PrintStream(clientSocket.getOutputStream())
                    .println(Message.echoIntro + message);
        }
    }

}
