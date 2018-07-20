package Core.Clients;

import java.io.*;

public class MessageEchoer implements Runnable {

    private BufferedReader reader;
    private PrintStream output;
    private boolean terminated;

    public MessageEchoer(BufferedReader reader, PrintStream output) {
        this.reader = reader;
        this.output = output;
        terminated = false;
    }

    public void run() {
        try {
            printMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRunning() {
        terminated = true;
    }

    private void printMessages() throws IOException {
        String message;
        while (((message = reader.readLine()) != null) && (!terminated)) {
            output.println(message);
        }
    }

}
