import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

    private InputStream stdIn;
    private PrintStream stdPrint;
    private ServerSocket serverSocket;
    private BufferedReader dataReceivedInClientSocketReader;
    private PrintStream dataSentOutThroughClientSocket;
    private List<Socket> clients;

    public Server(InputStream stdIn, PrintStream stdPrint, ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.stdIn = stdIn;
        this.stdPrint = stdPrint;
        clients = new ArrayList<>();
    }

    public void run() {
        start();
    }

    public void start() {
        try {
            Socket clientSocket = serverSocket.accept();
            stdPrint.println("Connected.");

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            new Thread(clientHandler).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
