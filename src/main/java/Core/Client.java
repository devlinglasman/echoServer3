package Core;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader stdInReader;
    private PrintStream stdOut;
    private PrintStream dataSentToSocketPrinter;
    private MessageListener messageListener;

    public Client(InputStream stdIn, PrintStream stdOut, Socket socket) throws IOException {
        stdInReader = new BufferedReader(new InputStreamReader(stdIn));
        this.stdOut = stdOut;
        dataSentToSocketPrinter = new PrintStream(socket.getOutputStream());
        this.socket = socket;
        startListening();
        startEchoing();
    }

    private void startListening() throws IOException {
        messageListener = new MessageListener(socket.getInputStream(),
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
