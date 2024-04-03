import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ReservationSystem {
    private static Map<String, String> reservations = new HashMap<>();
    private static int pnrCounter = 1000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;
        String username, password;

        while (!isLoggedIn) {
            System.out.println("Login Form");
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            if (authenticate(username, password)) {
                System.out.println("Login successful!");
                isLoggedIn = true;
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\n1. Reservation Form");
            System.out.println("2. Cancellation Form");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    makeReservation(scanner);
                    break;
                case 2:
                    cancelReservation(scanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        scanner.close();
    }

    private static boolean authenticate(String username, String password) {
        // Replace this with actual authentication logic (e.g., database lookup)
        return true;
    }

    private static void makeReservation(Scanner scanner) {
        System.out.println("\nReservation Form");
        System.out.print("Enter your basic details: ");
        String details = scanner.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey: ");
        String date = scanner.nextLine();
        System.out.print("Enter from (place): ");
        String from = scanner.nextLine();
        System.out.print("Enter destination: ");
        String to = scanner.nextLine();

        String pnr = "PNR" + pnrCounter++;
        reservations.put(pnr, details + ", Train: " + trainNumber + ", Class: " + classType + ", Date: " + date + ", From: " + from + ", To: " + to);
        System.out.println("Reservation successful. Your PNR number is: " + pnr);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.println("\nCancellation Form");
        System.out.print("Enter your PNR number: ");
        String pnr = scanner.nextLine();

        if (reservations.containsKey(pnr)) {
            System.out.println("Your reservation details: " + reservations.get(pnr));
            System.out.print("Press OK to confirm cancellation (or any other key to cancel): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("OK")) {
                reservations.remove(pnr);
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation cancelled.");
            }
        } else {
            System.out.println("Invalid PNR number. Please try again.");
        }
    }
}
