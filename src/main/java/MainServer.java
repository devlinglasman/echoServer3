import java.io.*;

public class MainServer {

    public static void main(String[] args) {
        InputStream dataIntoServerSocket = new ByteArrayInputStream("".getBytes());
        OutputStream dataOutOfServerSocket = new ByteArrayOutputStream();

        SocketStub socketStub = new SocketStub(dataIntoServerSocket, dataOutOfServerSocket);
        ServerSocketStub serverSocketStub = new ServerSocketStub(socketStub);
        Server server = new Server(stdIn, stdPrint, serverSocketStub);

        server.start();
    }
}
