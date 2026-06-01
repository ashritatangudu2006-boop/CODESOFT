import java.util.Scanner;

public class RuleBasedChatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chatbot: Hi! Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase();

            if (userInput.contains("hello") || userInput.contains("hi")) {
                System.out.println("Chatbot: Hello! How can I help you today?");
            } else if (userInput.contains("how are you")) {
                System.out.println("Chatbot: I'm just a program, but I'm doing great! How about you?");
            } else if (userInput.contains("bye") || userInput.contains("goodbye")) {
                System.out.println("Chatbot: Goodbye! Have a wonderful day!");
                break;
            } else if (userInput.contains("your name")) {
                System.out.println("Chatbot: I'm a simple rule-based chatbot.");
            } else if (userInput.contains("weather")) {
                System.out.println("Chatbot: I can't check live weather yet, but it's always sunny in my world!");
            } else {
                System.out.println("Chatbot: Sorry, I don't understand that. Can you rephrase?");
            }
        }

        scanner.close();
    }
}
