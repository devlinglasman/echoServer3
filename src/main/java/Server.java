import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private InputStream stdIn;
    private PrintStream stdPrint;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public Server(InputStream stdIn, PrintStream stdPrint, ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.stdIn = stdIn;
        this.stdPrint = stdPrint;
    }

    public void start() {
        try {
            clientSocket = serverSocket.accept();

            stdPrint.println(Message.clientConnected);
            PrintStream clientSocketOutput = new PrintStream(clientSocket.getOutputStream());
            clientSocketOutput.println(Message.clientConnected);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
