package Core.Clients;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executor;

public class Client {

    private BufferedReader stdInReader;
    private PrintStream stdOut;
    private BufferedReader socketReader;
    private PrintStream socketOut;
    private Executor executor;

    public Client(InputStream stdIn, PrintStream stdOut, Socket socketToServer, Executor executor) throws IOException {
        stdInReader = new BufferedReader(new InputStreamReader(stdIn));
        this.stdOut = stdOut;
        socketReader = new BufferedReader(new InputStreamReader(socketToServer.getInputStream()));
        socketOut = new PrintStream(socketToServer.getOutputStream());
        this.executor = executor;
    }

    public void go() {
        echoServerMessages();
        sendOwnMessages();
    }

    private void echoServerMessages() {
        executor.execute(new MessageEchoer(socketReader, stdOut));
    }

    private void sendOwnMessages() {
        executor.execute(new MessageEchoer(stdInReader, socketOut));
    }

}
