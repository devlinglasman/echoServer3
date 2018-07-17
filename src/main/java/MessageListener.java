import java.io.*;

public class MessageListener implements Runnable {

    private BufferedReader reader;
    private PrintStream output;
    private boolean stop;

    public MessageListener(InputStream inputStream, PrintStream output) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.output = output;
        stop = false;
    }

    public void run() {
        try {
            printMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMessages() throws IOException {
        String message;
        while (((message = reader.readLine()) != null) && (!stop)) {
            output.println(message);
        }
    }

    public void stopRunning() {
        stop = true;
    }
}
