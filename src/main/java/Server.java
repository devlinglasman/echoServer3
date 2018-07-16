import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;

public class Server {

    private InputStream stdIn;
    private PrintStream stdOut;
    private ServerSocket serverSocket;

    public Server(InputStream stdIn, PrintStream stdOut, ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.stdIn = stdIn;
        this.stdOut = stdOut;
    }

    public void start() {
        try {
            serverSocket.accept();

            stdOut.println(Message.clientConnected);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
