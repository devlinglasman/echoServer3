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

            printToTerminal(Message.clientConnected);

            String clientMessage = receiveClientMessage();
            printToTerminal(Message.messageFromClientIntro + clientMessage);
            printMessageToClient(clientMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printToTerminal(String message) {
        stdPrint.println(message);
    }

    public String receiveClientMessage() throws IOException {
        return dataReceivedInClientSocketReader.readLine();
    }

    public void printMessageToClient(String message) {
        dataSentOutThroughClientSocket.println(message);
    }
}
