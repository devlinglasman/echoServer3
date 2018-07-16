import java.io.*;
import java.net.Socket;

public class Client {

    private BufferedReader stdInReader;
    private PrintStream stdOut;
    private Socket socket;
    private BufferedReader dataReceivedFromSocketReader;
    private PrintStream dataSentToSocketPrinter;
    private String username;

    public Client(InputStream stdIn, PrintStream stdOut, Socket socket) throws IOException {
        stdInReader = new BufferedReader(new InputStreamReader(stdIn));
        this.stdOut = stdOut;
        this.socket = socket;
        connectSocket();
        setUsername();
        startEchoing();
    }

    public void connectSocket() {
        try {
            dataReceivedFromSocketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dataSentToSocketPrinter = new PrintStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startEchoing() {
        try {
            String message;
            while ((message = retrieveMessageFromTerminal()) != null) {
                writeMessageToSocket(message);
                printMessageFromSocket();

                if (message.equals("bye")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMessageToSocket(String message) {
        dataSentToSocketPrinter.println(message);
    }

    public void printMessageFromSocket() throws IOException {
        stdOut.println(dataReceivedFromSocketReader.readLine());
    }

    private String retrieveMessageFromTerminal() throws IOException {
        return stdInReader.readLine();
    }

    private void setUsername() throws IOException {
        stdOut.println(Message.askUserName);
        username = retrieveMessageFromTerminal();
        writeMessageToSocket(username);
    }
}
