import java.io.*;
import java.net.Socket;

public class Client {

    private BufferedReader stdInReader;
    private PrintStream stdPrint;
    private Socket socket;
    private BufferedReader dataReceivedFromSocketReader;
    private PrintStream dataSentToSocketPrinter;

    public Client(InputStream stdIn, PrintStream stdPrint, Socket socket) {
        stdInReader = new BufferedReader(new InputStreamReader(stdIn));
        this.stdPrint = stdPrint;
        this.socket = socket;
        connectSocket();
        startEchoing();
    }

    public void startEchoing() {
        try {
            String message;
            while ((message = retrieveMessageFromTerminal()) != null) {
                writeMessageToSocket(message);
                printMessageFromSocket();

                if (message.equals("Bye")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectSocket() {
        try {
            dataReceivedFromSocketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dataSentToSocketPrinter = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMessageToSocket(String message) {
        dataSentToSocketPrinter.println(message);
    }

    public void printMessageFromSocket() throws IOException {
        stdPrint.println(Message.echoIntro + dataReceivedFromSocketReader.readLine());
    }

    private String retrieveMessageFromTerminal() throws IOException {
        return stdInReader.readLine();
    }
}
