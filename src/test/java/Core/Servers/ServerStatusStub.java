package Core.Servers;

public class ServerStatusStub extends ServerStatus {

    private int numberOfClients;
    private int counter;

    public ServerStatusStub(int numberOfClients) {
        this.numberOfClients = numberOfClients;
        counter = 0;
    }

    @Override
    public boolean isRunning() {
        boolean isRunning = numberOfClients > counter;
        counter++;
        return isRunning;
    }

}
