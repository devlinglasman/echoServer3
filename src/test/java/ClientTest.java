import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class ClientTest {

    @Test
    public void writeMessagesToSocketFromTerminal() throws IOException {
        IOHelper stdIO = new IOHelper("Hello\nbye");
        IOHelper socketIO = new IOHelper("");

        SocketStub socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        new Client(stdIO.getIn(), stdIO.getOut(), socketStub);

        assertEquals( "Hello\n" + "bye\n", socketIO.getOutput());
    }

    @Test
    public void writeMessagesFromSocketToTerminal() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper("Hello\nbye");

        SocketStub socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        new Client(stdIO.getIn(), stdIO.getOut(), socketStub);

        assertEquals( "Hello\n" + "bye\n", stdIO.getOutput());
    }
}
