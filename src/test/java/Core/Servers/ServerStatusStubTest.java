package Core.Servers;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ServerStatusStubTest {

    @Test
    public void oneClientOneLoop() {
        ServerStatusStub serverStatusStub = new ServerStatusStub(1);

        assertTrue(serverStatusStub.isRunning());
    }

    @Test
    public void oneClientTwoLoops() {
        ServerStatusStub serverStatusStub = new ServerStatusStub(1);

        serverStatusStub.isRunning();

        assertFalse(serverStatusStub.isRunning());
    }
}
