import java.io.*;
import java.net.Socket;

public class Client {

    private InputStream stdIn;
    private PrintStream stdPrint;
    private Socket socket;

    public Client(InputStream stdIn, PrintStream stdPrint, Socket socket) {
        this.stdIn = stdIn;
        this.stdPrint = stdPrint;
        this.socket = socket;
    }

    public void receiveMessage() {
        try {
            InputStream dataReceivedFromSocket = socket.getInputStream();
            BufferedReader dataReceivedReader = new BufferedReader(new InputStreamReader(dataReceivedFromSocket));
            stdPrint.println(dataReceivedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
