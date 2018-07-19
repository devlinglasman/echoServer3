package Core.Clients;

import Core.IOHelper;
import Core.Message;
import Core.SocketStub;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ClientConnectionsTest {

    @Test
    public void broadcast() throws IOException {
        IOHelper socketIOOne = new IOHelper("");
        SocketStub clientSocketOne = new SocketStub(socketIOOne.getIn(), socketIOOne.getOut());
        IOHelper socketIOTwo = new IOHelper("");
        SocketStub clientSocketTwo = new SocketStub(socketIOTwo.getIn(), socketIOTwo.getOut());

        List<Socket> clientList = Arrays.asList(clientSocketOne, clientSocketTwo);
        ClientConnections clientConnections = new ClientConnections(clientList);

        clientConnections.broadcast(Message.clientConnected());

        assertEquals(Message.echoIntro + Message.clientConnected() + "\n",
                socketIOOne.getOutput());
        assertEquals(Message.echoIntro + Message.clientConnected() + "\n",
                socketIOTwo.getOutput());
    }
}
