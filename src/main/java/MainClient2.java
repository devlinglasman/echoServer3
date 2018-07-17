import java.io.IOException;
import java.net.Socket;

public class MainClient2 {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 6666);
        new Client(System.in, System.out, socket);
    }
}
