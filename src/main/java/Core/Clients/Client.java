package Core.Clients;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executor;

public class Client {

    private BufferedReader stdInReader;
    private PrintStream stdOut;
    private BufferedReader socketReader;
    private PrintStream socketOut;
    private MessageEchoer messageEchoer;
    private Executor executor;

    public Client(InputStream stdIn, PrintStream stdOut, Socket socketToServer, Executor executor) throws IOException {
        stdInReader = new BufferedReader(new InputStreamReader(stdIn));
        this.stdOut = stdOut;
        socketReader = new BufferedReader(new InputStreamReader(socketToServer.getInputStream()));
        socketOut = new PrintStream(socketToServer.getOutputStream());
        this.executor = executor;
        go();
    }


    public void go() {
        startListening();
        startEchoing();
    }

    private void startListening() {
        executor.execute(new MessageEchoer(socketReader, stdOut));
    }

    public void startEchoing() {
        try {
            String message;
            while ((message = stdInReader.readLine()) != null) {
                socketOut.println(message);

                if (message.equals("bye")) {
                    messageEchoer.stopRunning();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
