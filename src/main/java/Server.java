import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private InputStream stdIn;
    private PrintStream stdPrint;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader dataReceivedInClientSocketReader;
    private PrintStream dataSentOutThroughClientSocket;

    public Server(InputStream stdIn, PrintStream stdPrint, ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.stdIn = stdIn;
        this.stdPrint = stdPrint;
    }

    public void start() {
        try {
            clientSocket = serverSocket.accept();
            dataReceivedInClientSocketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            dataSentOutThroughClientSocket = new PrintStream(clientSocket.getOutputStream());

            stdPrint.println(Message.clientConnected);
            dataSentOutThroughClientSocket.println(Message.clientConnected);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void receiveClientMessage() throws IOException {
        String clientDataReceived = dataReceivedInClientSocketReader.readLine();
        stdPrint.println(clientDataReceived);
    }
}
