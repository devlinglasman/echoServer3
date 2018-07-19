package Core;

import java.io.*;

public class IOHelper {

    private InputStream in;
    private OutputStream outputStream;
    private PrintStream out;

    public IOHelper(String dataInput) {
        in = new ByteArrayInputStream(dataInput.getBytes());
        outputStream = new ByteArrayOutputStream();
        out = new PrintStream(outputStream);
    }

    public String getOutput() {
        return outputStream.toString();
    }

    public InputStream getIn() {
        return in;
    }

    public PrintStream getOut() {
        return out;
    }
}
