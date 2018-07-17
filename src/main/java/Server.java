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
            while (true) {
                Socket clientSocket = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(this, clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();

                stdPrint.println(thread.getName() + "Connected.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addClient(Socket socket) {
        clients.add(socket);
    }

    public void broadcastMessage(String message) {
        for (Socket client : clients) {
            try {
                PrintStream writer = new PrintStream(client.getOutputStream());
                writer.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
