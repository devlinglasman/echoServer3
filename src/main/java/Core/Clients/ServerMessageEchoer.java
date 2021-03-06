package Core.Clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ServerMessageEchoer implements Runnable {

    private BufferedReader reader;
    private PrintStream out;

    public ServerMessageEchoer(BufferedReader reader, PrintStream out) {
        this.out = out;
        this.reader = reader;
    }

    public void run() {
        while (true) {
            try {
                executeMessage(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void executeMessage(String message) {
            out.println(message);
    }

}
