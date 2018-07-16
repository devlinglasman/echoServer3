import java.io.*;
import java.net.Socket;

public class MainClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 6666);
        Client client = new Client(System.in, System.out, socket);
        client.printMessageFromSocket();
        client.writeMessageToSocket();
    }
}
