package Core.Clients;

import Core.IOHelper;
import Core.Message;
import Core.Servers.SynchronousExecutor;
import Core.SocketStub;
import org.junit.Test;

import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static junit.framework.TestCase.assertEquals;

public class ClientTest {

    private final String helloWorld = "Hello world!\n";

    @Test
    public void echoMessageFromServer() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper(helloWorld);

        SocketStub socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        Client client = new Client(stdIO.getIn(), stdIO.getOut(), socketStub, new SynchronousExecutor());

        client.go();

        assertEquals(helloWorld, stdIO.getOutput());
    }

    @Test
    public void sendMessageFromTerminalToServer() throws IOException {
        IOHelper stdIO = new IOHelper(helloWorld);
        IOHelper socketIO = new IOHelper("");

        SocketStub socketStub = new SocketStub(socketIO.getIn(), socketIO.getOut());
        Client client = new Client(stdIO.getIn(), stdIO.getOut(), socketStub, new SynchronousExecutor());

        client.go();

        assertEquals(helloWorld, socketIO.getOutput());
    }
}
