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
            clients.add(serverSocket.accept());
            stdPrint.println("Connected.");
            runClientMechanics();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runClientMechanics() throws IOException {
        for (Socket client : clients) {

            dataReceivedInClientSocketReader = setReader(client.getInputStream());
            dataSentOutThroughClientSocket = setOut(client.getOutputStream());

            String clientMessage;
            while ((clientMessage = receiveClientMessage()) != null) {
                broadcastMessage(clientMessage);
            }
        }
    }

    public void broadcastMessage(String clientMessage) throws IOException {
        printToTerminal(Message.clientSays() + clientMessage);
        printMessageToAllClients(Message.clientSays() + clientMessage);
    }

    public void printToTerminal(String message) {
        stdPrint.println(message);
    }

    public String receiveClientMessage() throws IOException {
        return dataReceivedInClientSocketReader.readLine();
    }

    public void printMessageToAllClients(String message) throws IOException {
        for (Socket client : clients) {
            new PrintStream(setOut(client.getOutputStream())).println(message);
        }
    }

    private BufferedReader setReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }

    private PrintStream setOut(OutputStream out) {
        return new PrintStream(out);
    }
}
