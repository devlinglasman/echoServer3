import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class ClientTest {

    @Test
    public void receiveMessageFromServerPrintToTerminalWithEchoIntroInFront() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper("Hello");

        SocketStub socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        new Client(stdIO.getIn(), stdIO.getOut(), socketStub);

        assertEquals(Message.echoIntro + "Hello\n", stdIO.getOutput());
    }

    @Test
    public void writeMessageToSocketFromTerminal() throws IOException {
        IOHelper stdIO = new IOHelper("Hello");
        IOHelper socketIO = new IOHelper("");

        SocketStub socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        new Client(stdIO.getIn(), stdIO.getOut(), socketStub);

        assertEquals("Hello\n", socketIO.getOutput());
    }
}
