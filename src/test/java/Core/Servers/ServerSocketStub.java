package Core.Servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerSocketStub extends ServerSocket {

    private List<Socket> clientSockets;
    private int clientCounter;

    public ServerSocketStub(List<Socket> clientSockets) throws IOException {
        this.clientSockets = clientSockets;
        clientCounter = 0;
    }

    @Override
    public Socket accept() {
        Socket socket = clientSockets.get(clientCounter);
        clientCounter++;
        return socket;
    }
}
