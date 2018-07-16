import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class ClientTest {

    @Test
    public void echoMessagesSentThroughSocket() throws IOException {
        IOHelper stdIO = new IOHelper("Hello\nbye");
        IOHelper socketIO = new IOHelper("");

        SocketStub socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        new Client(stdIO.getIn(), stdIO.getOut(), socketStub);

        assertEquals( "Hello\n" + "bye\n", socketIO.getOutput());
    }
}
