package Core.Clients;

import Core.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ClientProtocol implements Runnable {

    private BufferedReader reader;
    private PrintStream out;

public ClientProtocol(BufferedReader reader, PrintStream out) {
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
        if (message == null || message.equals("bye")) {
            System.out.println("closed");
        }
        else {
            out.println(message);
        }
    }
}
