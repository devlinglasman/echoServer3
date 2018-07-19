package Core.Server;

import Core.Clients.ClientConnections;
import Core.IOHelper;
import Core.Message;
import Core.SocketStub;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class eachClientConnectionThreadTest {

    private final String helloWorld = "Hello world!\n";

    @Test
    public void listenAndBroadcast() throws IOException {
        IOHelper socketIOOne = new IOHelper(helloWorld);
        SocketStub clientSocketOne = new SocketStub(socketIOOne.getIn(), socketIOOne.getOut());

        IOHelper socketIOTwo = new IOHelper("");
        SocketStub clientSocketTwo = new SocketStub(socketIOTwo.getIn(), socketIOTwo.getOut());

        List<Socket> clientList = Arrays.asList(clientSocketOne, clientSocketTwo);
        ClientConnections clientConnections = new ClientConnections(clientList);

        EachClientConnectionThread eachClientConnectionThread =
                new EachClientConnectionThread(clientConnections, clientSocketOne);

        eachClientConnectionThread.run();

        assertEquals(Message.echoIntro + helloWorld, socketIOOne.getOutput());
        assertEquals(Message.echoIntro + helloWorld, socketIOTwo.getOutput());
    }
}