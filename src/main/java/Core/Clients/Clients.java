package Core.Clients;

import Core.Message;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class Clients {

    private List<Client> clients;

    public Clients(List<Client> clients) {
        this.clients = clients;
    }

    public void broadcast(String message) throws IOException {
        for (Client client : clients) {
            new PrintStream(client.getSocket().getOutputStream())
                    .println(Message.echoIntro + message);
        }
    }
}
