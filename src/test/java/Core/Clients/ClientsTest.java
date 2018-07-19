package Core.Clients;

import Core.IOHelper;
import Core.Message;
import Core.SocketStub;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ClientsTest {

    @Test
    public void broadcast() throws IOException {
        IOHelper stdIOOne = new IOHelper("");
        IOHelper socketIOOne = new IOHelper("");

        SocketStub socketStubOne = new SocketStub(socketIOOne.getIn(), socketIOOne.getOut());
        Client clientOne = new Client(stdIOOne.getIn(), stdIOOne.getOut(), socketStubOne);

        IOHelper stdIOTwo = new IOHelper("");
        IOHelper socketIOTwo = new IOHelper("");

        SocketStub socketStubTwo = new SocketStub(socketIOTwo.getIn(), socketIOTwo.getOut());
        Client clientTwo = new Client(stdIOTwo.getIn(), stdIOTwo.getOut(), socketStubTwo);

        List<Client> clientList = Arrays.asList(clientOne, clientTwo);
        Clients clients = new Clients(clientList);

        clients.broadcast(Message.clientConnected());

        assertEquals(Message.echoIntro + Message.clientConnected() + "\n",
                socketIOOne.getOutput());
    }
}
