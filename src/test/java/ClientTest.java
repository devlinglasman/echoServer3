import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class ClientTest {

    @Test
    public void echoMessagesSentThroughSocket() {
        IOHelper stdIO = new IOHelper("Hello\nBye");
        IOHelper socketIO = new IOHelper("");

        SocketStub socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        new Client(stdIO.getIn(), stdIO.getOut(), socketStub);

        assertEquals( "Hello\n" + "Bye\n", socketIO.getOutput());
    }
}
