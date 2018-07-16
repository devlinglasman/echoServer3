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
        echo();
    }

    public void echo() {
        try {
            writeMessageToSocket();
            printMessageFromSocket();
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

    public void writeMessageToSocket() throws IOException {
        String terminalInput = stdInReader.readLine();
        dataSentToSocketPrinter.println(terminalInput);
    }

    public void printMessageFromSocket() throws IOException {
        stdPrint.println(dataReceivedFromSocketReader.readLine());
    }
}
