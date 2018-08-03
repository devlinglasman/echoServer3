package Core;

import Core.Clients.ServerMessageEchoer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class InputReader {

    private ServerMessageEchoer serverMessageEchoer;

    public InputReader(ServerMessageEchoer serverMessageEchoer) {
        this.serverMessageEchoer = serverMessageEchoer;
    }

    public void read(BufferedReader in) {
        String message;
        try {
            while (true) {
                message = in.readLine();
                serverMessageEchoer.executeMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
