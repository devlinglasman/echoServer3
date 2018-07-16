import java.io.*;

public class IOHelper {

    private InputStream in;
    private OutputStream out;
    private PrintStream print;

    public IOHelper(String dataInput) {
        in = new ByteArrayInputStream(dataInput.getBytes());
        out = new ByteArrayOutputStream();
        print = new PrintStream(out);
    }

    public String getOutput() {
        return out.toString();
    }

    public InputStream getIn() {
        return in;
    }

    public PrintStream getOut() {
        return print;
    }
}
