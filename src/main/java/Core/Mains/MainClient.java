package Core.Mains;

import Core.Clients.Client;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executors;

public class MainClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 6666);
        Client client = new Client(System.in, System.out, socket, Executors.newCachedThreadPool());
        client.go();
    }
}
