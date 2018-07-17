import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private PrintStream stdPrint;
    private ServerSocket serverSocket;

    public Server(PrintStream stdPrint, ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.stdPrint = stdPrint;
        start();
    }

    public void start() {
        try {
            Socket clientSocket = serverSocket.accept();
            broadcastMessages(clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void broadcastMessages(Socket clientSocket) throws IOException {
        BufferedReader dataReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintStream dataWriter = new PrintStream(clientSocket.getOutputStream());
        String clientMessage;
        while ((clientMessage = dataReader.readLine()) != null) {
            dataWriter.println(clientMessage);
            printToTerminal(clientMessage);
        }
    }

    public void printToTerminal(String message) {
        stdPrint.println(message);
    }
}
