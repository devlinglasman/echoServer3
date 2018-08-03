package Core.Clients;

import Core.IOHelper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;

public class ServerMessageEchoerTest {

    private final String helloWorld = "Hello World!\n";
//
//    @Test
//    public void echoMessage_fromSocketToTerminal() {
//        IOHelper stdIO = new IOHelper("");
//        IOHelper socketIO = new IOHelper(helloWorld);
//        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socketIO.getIn()));
//
//        ServerMessageEchoer serverMessageEchoer = new ServerMessageEchoer(socketReader, stdIO.getOut());
//
//        serverMessageEchoer.run();
//
//        assertEquals(helloWorld, stdIO.getOutput());
//    }
//
//    @Test
//    public void noMessagesIfStopActivated() {
//        IOHelper stdIO = new IOHelper("");
//        IOHelper socketIO = new IOHelper(helloWorld);
//        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socketIO.getIn()));
//
//        ServerMessageEchoer serverMessageEchoer = new ServerMessageEchoer(socketReader, stdIO.getOut());
//
//        serverMessageEchoer.run();
//
//        assertEquals( "", stdIO.getOutput());
//    }
}
