package Core.Servers;

import Core.IOHelper;
import Core.Message;
import Core.SocketStub;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static junit.framework.TestCase.assertEquals;

public class ServerTest {

    private final String helloWorld = "Hello World!\n";

    @Test
    public void serverAcceptsTwoClients() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIOclient1 = new IOHelper("");
        IOHelper socketIOclient2 = new IOHelper("");

        Socket clientSocket1 = new SocketStub(socketIOclient1.getIn(), socketIOclient1.getOut());
        Socket clientSocket2 = new SocketStub(socketIOclient2.getIn(), socketIOclient2.getOut());
        ServerSocket serverSocketStub = new ServerSocketStub(Arrays.asList(clientSocket1, clientSocket2));
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        new Server(stdIO.getOut(), serverSocketStub, new ServerStatusStub(2), executorService);

        assertEquals(Message.clientConnected() + "\n"
                + Message.clientConnected() + "\n", stdIO.getOutput());
    }

    @Test
    public void serverEchoesClientMessage() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIOclient = new IOHelper(helloWorld);

        Socket clientSocket = new SocketStub(socketIOclient.getIn(), socketIOclient.getOut());
        ServerSocket serverSocketStub = new ServerSocketStub(Arrays.asList(clientSocket));
        Executor executor = new SynchronousExecutor();

        new Server(stdIO.getOut(), serverSocketStub, new ServerStatusStub(1), executor);

        assertEquals(Message.echoIntro + helloWorld, socketIOclient.getOutput());
    }
}
//
//    @Test
//    public void addClient() throws IOException {
//        IOHelper stdIO = new IOHelper("");
//        IOHelper socketIOclient1 = new IOHelper("");
//
//        IOHelper stdIOclient2 = new IOHelper("");
//        IOHelper socketIOclient2 = new IOHelper("");
//
//        Socket clientSocket1 = new SocketStub(socketIOclient2.getIn(), socketIOclient2.getOut());
//        ServerSocket serverSocketStub = new ServerSocketStub(Arrays.asList(clientSocket1));
//        new Servers(stdIO.getOut(), serverSocketStub, new ServerStatusStub(1));
//
//
//
//        assertEquals("Hello\n", socketIOclient2.getOutput());
//    }
////    @Test
////    public void serverAcceptsTwoClients() throws IOException {
////        Core.IOHelper socketIOclient1 = new Core.IOHelper("");
////        Socket clientSocket1 = new Core.SocketStub(socketIOclient1.getIn(), socketIOclient1.getOut());
////
////        Core.IOHelper socketIOclient2 = new Core.IOHelper("");
////        Socket clientSocket2 = new Core.SocketStub(socketIOclient2.getIn(), socketIOclient2.getOut());
////
////        List<Socket> clientSockets = Arrays.asList(clientSocket1, clientSocket2);
////
////        ServerSocket serverSocketStub = new ServerSocketStub(clientSockets);
////        Core.IOHelper stdIO = new Core.IOHelper("");
////        new Core.Servers.Servers(stdIO.getOut(), serverSocketStub);
////        new Servers(stdIOclient1.getOut(), serverSocketStub, new ServerStatusStub(1));
////
////        assertEquals(Core.Message.clientConnected() + "\n"
////                + Core.Message.clientConnected() + "\n", stdIO.getOutput());
////    }
//}
