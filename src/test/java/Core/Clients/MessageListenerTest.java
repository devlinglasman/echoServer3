package Core.Clients;

import Core.IOHelper;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class MessageListenerTest {

    @Test
    public void writeMessagesFromSocketToTerminal() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper("Hello");

        MessageListener messageListener = new MessageListener(socketIO.getIn(), stdIO.getOut());

        messageListener.printMessages();

        TestCase.assertEquals( "Hello\n", stdIO.getOutput());
    }

    @Test
    public void noMessagesIfStopActivated() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper("Hello");

        MessageListener messageListener = new MessageListener(socketIO.getIn(), stdIO.getOut());
        messageListener.stopRunning();

        messageListener.printMessages();

        TestCase.assertEquals( "", stdIO.getOutput());
    }
}
