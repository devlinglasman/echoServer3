import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;

public class MessageListenerTest {

    @Test
    public void writeMessagesFromSocketToTerminal() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper("Hello");

        MessageListener messageListener = new MessageListener(socketIO.getIn(), stdIO.getOut());

        messageListener.printMessages();

        assertEquals( "Hello\n", stdIO.getOutput());
    }

    @Test
    public void noMessagesIfStopActivated() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper("Hello");

        MessageListener messageListener = new MessageListener(socketIO.getIn(), stdIO.getOut());
        messageListener.stopRunning();

        messageListener.printMessages();

        assertEquals( "", stdIO.getOutput());
    }
}
