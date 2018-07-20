package Core.Clients;

import Core.IOHelper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;

public class MessageEchoerTest {

    private final String helloWorld = "Hello World!\n";

    @Test
    public void echoMessage_fromSocketToTerminal() {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper(helloWorld);
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socketIO.getIn()));

        MessageEchoer messageEchoer = new MessageEchoer(socketReader, stdIO.getOut());

        messageEchoer.run();

        assertEquals(helloWorld, stdIO.getOutput());
    }

    @Test
    public void noMessagesIfStopActivated() {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper(helloWorld);
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socketIO.getIn()));

        MessageEchoer messageEchoer = new MessageEchoer(socketReader, stdIO.getOut());
        messageEchoer.stopRunning();

        messageEchoer.run();

        assertEquals( "", stdIO.getOutput());
    }
}
