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
    private Server server;

    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        dataReceivedInClientSocketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        dataSentOutThroughClientSocket = new PrintStream(socket.getOutputStream());
    }

    public void run() {
        server.addClient(socket);
        try {
            runClientMechanics();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runClientMechanics() throws IOException {
        String clientMessage;
        while ((clientMessage = receiveClientMessage()) != null) {
            server.broadcastMessage(clientMessage);
        }
    }

    public String receiveClientMessage() throws IOException {
        return dataReceivedInClientSocketReader.readLine();
    }


}
