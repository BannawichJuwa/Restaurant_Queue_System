import java.util.Scanner;

public class Main {

    static CustomerQueue queue = new CustomerQueue();
    static Scanner sc = new Scanner(System.in);
    static int customerIdCounter = 1;

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("     Restaurant Queue System     ");
        System.out.println("================================");
        int choice;
        do {
            printMenu();
            choice = getIntInput("Choose: ");
            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> callNextCustomer();
                case 3 -> viewQueue();
                case 4 -> peekNext();
                case 5 -> searchCustomer();       // Iterative
                case 6 -> longestWaiting();        // Recursive
                case 7 -> viewCustomerHistory();   // View history
                case 0 -> System.out.println("\n[System closed. Thank you]");
                default -> System.out.println("Invalid option. Please try again");
            }
        } while (choice != 0);
        sc.close();
    }

    static void printMenu() {
        System.out.println("\n---------------------------------------------");
        System.out.println ("[Please choose an option]");
        System.out.println("\nMain Menu:");
        System.out.println("1. Add Customer to Queue");
        System.out.println("2. Call Next Customer");
        System.out.println("3. View All Queue");
        System.out.println("4. View Next Customer (No Call)");
        System.out.println("5. Search Customer in Queue");
        System.out.println("6. View Longest Waiting Customer");
        System.out.println("7. View Customer History");
        System.out.println("0. Exit System");
        System.out.println("---------------------------------------------");
    }

    // case 1: Add Customer 
    static void addCustomer() {
        System.out.print("Customer Name: ");
        String name = sc.nextLine().trim();
        int tableSize = getIntInput("Party Size: ");
        Customer c = new Customer(customerIdCounter++, name, tableSize);
        queue.enqueue(c);
        System.out.println("Added " + name + " to queue (Queue #" + queue.getSize() + ")");
    }

    //  case 2: Call Next Customer 
    static void callNextCustomer() {
        Customer c = queue.dequeue();
        if (c != null) {
            System.out.println("Please welcome " + c.getName()
                + " (Table for " + c.getTableSize() + " people) to the restaurant");
        }
    }

    // case 3: View Queue
    static void viewQueue() {
        queue.display();
    }

    // case 4: Peek 
    static void peekNext() {
        Customer c = queue.peek();
        if (c != null) {
            System.out.println("Next Customer: " + c);
        } else {
            System.out.println("No customers in queue");
        }
    }

    // case 5: Search Customer [Iterative] 
    static void searchCustomer() {
        System.out.print("Search Customer Name: ");
        String name = sc.nextLine().trim();
        int pos = queue.searchByName(name);
        if (pos != -1) {
            System.out.println("Found \"" + name + "\" Queue No. " + pos
                + " (waiting for " + (pos - 1) + " more people)");
        } else {
            System.out.println("Not found \"" + name + "\" in queue");
        }
    }

    // case 6: Longest Waiting Customer [Recursive] 
    static void longestWaiting() {
        Customer c = queue.findLongestWaiting();
        if (c != null) {
            int pos = queue.searchByName(c.getName());
            System.out.println("The longest waiting customer is: " + c);
            System.out.println("Position: " + pos + " (waiting for " + (pos - 1) + " more people in queue)");
        } 
        else {
            System.out.println("No customers in queue");
        }
    }

    // case 7: View Customer History
    static void viewCustomerHistory() {
        queue.displayHistory();
    }

    // helper: Get number with validation 
    static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from the main menu.");
            }
        }
    }
}
