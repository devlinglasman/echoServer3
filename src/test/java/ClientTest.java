import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class ClientTest {

    @Test
    public void receiveMessage() {
        InputStream stdIn = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream stdOut = new ByteArrayOutputStream();
        PrintStream stdPrint = new PrintStream(stdOut);

        InputStream dataIntoSocket = new ByteArrayInputStream(Message.clientConnected.getBytes());
        OutputStream dataOutOfSocket = new ByteArrayOutputStream();

        SocketStub socketStub = new SocketStub(dataIntoSocket, dataOutOfSocket);
        Client client = new Client(stdIn, stdPrint, socketStub);

        client.receiveMessage();

        assertEquals(Message.clientConnected + "\n", stdOut.toString());

    }

}
