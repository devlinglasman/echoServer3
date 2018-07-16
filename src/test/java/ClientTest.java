import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class ClientTest {

    @Test
    public void receiveMessage() throws IOException {
        InputStream stdIn = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream stdOut = new ByteArrayOutputStream();
        PrintStream stdPrint = new PrintStream(stdOut);

        InputStream dataIntoSocket = new ByteArrayInputStream(Message.clientConnected.getBytes());
        OutputStream dataOutOfSocket = new ByteArrayOutputStream();

        SocketStub socketStub = new SocketStub(dataIntoSocket, dataOutOfSocket);
        Client client = new Client(stdIn, stdPrint, socketStub);

        client.printMessageFromSocket();

        assertEquals(Message.clientConnected + "\n", stdOut.toString());
    }

    @Test
    public void writeMessageToSocketFromTerminal() throws IOException {
        InputStream stdIn = new ByteArrayInputStream("Hello".getBytes());
        ByteArrayOutputStream stdOut = new ByteArrayOutputStream();
        PrintStream stdPrint = new PrintStream(stdOut);

        InputStream dataIntoSocket = new ByteArrayInputStream("".getBytes());
        OutputStream dataOutOfSocket = new ByteArrayOutputStream();

        SocketStub socketStub = new SocketStub(dataIntoSocket, dataOutOfSocket);
        Client client = new Client(stdIn, stdPrint, socketStub);

        client.writeMessageToSocket();

        assertEquals("Hello\n", socketStub.getOutputStream().toString());
    }
}
