import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class ClientTest {

    @Test
    public void receiveMessageFromServerPrintToTerminal() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper(Message.clientConnected);

        SocketStub socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        Client client = new Client(stdIO.getIn(), stdIO.getOut(), socketStub);

        client.printMessageFromSocket();

        assertEquals(Message.clientConnected + "\n", stdIO.getOutput());
    }

    @Test
    public void writeMessageToSocketFromTerminal() throws IOException {
        IOHelper stdIO = new IOHelper("Hello");
        IOHelper socketIO = new IOHelper("");

        SocketStub socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        Client client = new Client(stdIO.getIn(), stdIO.getOut(), socketStub);

        client.writeMessageToSocket();

        assertEquals("Hello\n", socketIO.getOutput());
    }
}
