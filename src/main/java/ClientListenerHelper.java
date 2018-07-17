import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ClientListenerHelper implements Runnable {

    private PrintStream stdOut;
    private BufferedReader socketData;
    private boolean stop;

    public ClientListenerHelper(PrintStream stdOut, BufferedReader socketData) {
        this.stdOut = stdOut;
        this.socketData = socketData;
        this.stop = false;
    }

    public void run() {
        try {
            printMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMessages() throws IOException {
        String message;
        while (((message = socketData.readLine()) != null) && (!stop)) {
            stdOut.println(message);
        }
    }

    public void stopRunning() {
        stop = true;
    }
}
