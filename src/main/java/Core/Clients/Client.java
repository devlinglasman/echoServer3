package Core.Clients;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socketToServer;
    private BufferedReader stdInReader;
    private PrintStream stdOut;
    private PrintStream dataSentToSocketPrinter;
    private MessageListener messageListener;

    public Client(InputStream stdIn, PrintStream stdOut, Socket socketToServer) throws IOException {
        stdInReader = new BufferedReader(new InputStreamReader(stdIn));
        this.stdOut = stdOut;
        dataSentToSocketPrinter = new PrintStream(socketToServer.getOutputStream());
        this.socketToServer = socketToServer;
        startListening();
        startEchoing();
    }

    private void startListening() throws IOException {
        messageListener = new MessageListener(socketToServer.getInputStream(),
                new PrintStream(stdOut));
        new Thread(messageListener).start();
    }

    public void startEchoing() {
        try {
            String message;
            while ((message = stdInReader.readLine()) != null) {
                dataSentToSocketPrinter.println(message);

                if (message.equals("bye")) {
                    messageListener.stopRunning();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
