public class Message {

    public static String clientConnected(String username) {
        return username + " connected.";
    }

    public static String clientSays(String username) {
        return "Message from " + username + ": ";
    }

    public static final String echoIntro =
            "Echo: ";

    public static final String askUserName =
            "Hi there! Please enter a username below and then start chatting. " +
                    "By the way, enter 'bye' at any time to close the chat.";
}
