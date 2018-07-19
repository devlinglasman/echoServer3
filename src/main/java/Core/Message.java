package Core;

public class Message {

    public static String clientConnected() {
        return " connected.";
    }

    public static String clientSays() {
        return "Message from: ";
    }

    public static final String echoIntro =
            "Echo: ";

    public static final String askUserName =
            "Hi there! Please enter a username below and then start chatting. " +
                    "By the way, enter 'bye' at any time to close the chat.";
}
