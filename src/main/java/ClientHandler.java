import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {

    private Socket socket;
    private InputStream stdIn;
    private PrintStream stdPrint;
    private BufferedReader dataReceivedInClientSocketReader;
    private PrintStream dataSentOutThroughClientSocket;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        dataReceivedInClientSocketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        dataSentOutThroughClientSocket = new PrintStream(socket.getOutputStream());
    }

    public void run() {
        try {
            runClientMechanics();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runClientMechanics() throws IOException {
        String clientMessage;
        while ((clientMessage = receiveClientMessage()) != null) {
            broadcastMessage(clientMessage);

        }
    }

    public void broadcastMessage(String clientMessage) throws IOException {
        printMessageToAllClients(Message.clientSays() + clientMessage);
    }

//    public void printToTerminal(String message) {
//        stdPrint.println(message);
//    }

    public String receiveClientMessage() throws IOException {
        return dataReceivedInClientSocketReader.readLine();
    }

    public void printMessageToAllClients(String message) throws IOException {
            dataSentOutThroughClientSocket.println(message);
        }

}
