package Core.Clients;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executor;

public class Client {

    private BufferedReader stdInReader;
    private PrintStream stdOut;
    private Executor executor;
    private Socket socketToServer;

    public Client(InputStream stdIn, PrintStream stdOut, Socket socketToServer, Executor executor) {
        this.socketToServer = socketToServer;
        stdInReader = new BufferedReader(new InputStreamReader(stdIn));
        this.stdOut = stdOut;
        this.executor = executor;
    }

    public void start() {
        echoServerMessages();
        sendOwnMessages();
    }

    private void echoServerMessages() {
        try {
            executor.execute(new ServerMessageEchoer(new BufferedReader(new InputStreamReader
                    (socketToServer.getInputStream())), stdOut));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendOwnMessages() {
        try {
            executor.execute(new ClientProtocol(stdInReader,
                    new PrintStream(socketToServer.getOutputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
