import java.io.*;
import java.net.Socket;

public class Client {

    private BufferedReader stdInReader;
    private PrintStream stdOut;
    private BufferedReader dataReceivedFromSocketReader;
    private PrintStream dataSentToSocketPrinter;
    private ClientListenerHelper clientListenerHelper;

    public Client(InputStream stdIn, PrintStream stdOut, Socket socket) throws IOException {
        stdInReader = new BufferedReader(new InputStreamReader(stdIn));
        this.stdOut = stdOut;
        dataReceivedFromSocketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        dataSentToSocketPrinter = new PrintStream(socket.getOutputStream());
        startListening();
        startEchoing();
    }

    private void startListening() {
        clientListenerHelper = new ClientListenerHelper(stdOut, dataReceivedFromSocketReader);
        new Thread(clientListenerHelper).start();
    }

    public void startEchoing() {
        try {
            String message;
            while ((message = retrieveMessageFromTerminal()) != null) {
                writeMessageToSocket(message);
                if (message.equals("bye")) {
                    clientListenerHelper.stopRunning();
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

    private String retrieveMessageFromTerminal() throws IOException {
        return stdInReader.readLine();
    }

}
