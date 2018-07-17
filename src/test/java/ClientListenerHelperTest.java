import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;

public class ClientListenerHelperTest {

    @Test
    public void writeMessagesFromSocketToTerminal() throws IOException {
        IOHelper stdIO = new IOHelper("");
        IOHelper socketIO = new IOHelper("Hello");
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socketIO.getIn()));

        ClientListenerHelper clientListenerHelper = new ClientListenerHelper(stdIO.getOut(), socketReader);

        clientListenerHelper.printMessages();

        assertEquals( "Hello\n", stdIO.getOutput());
    }
}
