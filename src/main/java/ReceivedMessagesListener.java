import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ReceivedMessagesListener implements Runnable {

    private BufferedReader reader;
    private PrintStream stdOut;

    public ReceivedMessagesListener(BufferedReader reader, PrintStream stdOut) {
        this.reader = reader;
        this.stdOut = stdOut;
    }

    public void run() {
        String message;
        try {
            while ((message = reader.readLine()) != null) {
                stdOut.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
