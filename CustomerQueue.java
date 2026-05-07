import java.util.ArrayList;
import java.util.List;

public class CustomerQueue {

    private Node head;
    private Node tail;
    private int size;
    private List<Customer> history;  // Keep track of called customers

    public CustomerQueue() {
        head = null;
        tail = null;
        size = 0;
        history = new ArrayList<>();
    }

    // ENQUEUE: Add customer to queue (at the end)
    public void enqueue(Customer c) {
        Node newNode = new Node(c);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    // DEQUEUE: Remove customer from queue (from the head)
    public Customer dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. No customers");
            return null;
        }
        Customer served = head.getData();
        served.setCalled(true);  // Mark as called
        history.add(served);     // Add to history
        head = head.getNext();
        if (head == null) tail = null;
        size--;
        return served;
    }


    // PEEK: View first customer without removing
    public Customer peek() {
        if (isEmpty()) return null;
        return head.getData();
    }

    // DISPLAY: Display all queue
    public void display() {
        if (isEmpty()) {
            System.out.println("No customers in queue");
            return;
        }
        Node current = head;
        int position = 1;
        System.out.println("=== All Customers (" + size + " people) ===");
        while (current != null) {
            System.out.println(position + ". " + current.getData());
            current = current.getNext();
            position++;
        }
    }

    // -------------------------------------------------------
    // ITERATIVE: Search customer in queue by name
    //
    // Loop through every node from head to tail
    // Compare names one by one until found or end of queue
    //
    // Returns position in queue (1-based), -1 if not found
    // -------------------------------------------------------
    public int searchByName(String name) {
        Node current = head;
        int position = 1;

        while (current != null) {
            if (current.getData().getName().equalsIgnoreCase(name)) {
                return position;  // Found. Stop loop and return position
            }
            current = current.getNext();
            position++;
        }
        return -1;  // Loop completed. Not found
    }

    // -------------------------------------------------------
    // RECURSIVE: Find the longest waiting customer
    //
    // Recursively go deep until tail (base case) first
    // Then count position on the way back up
    // This way we know which node is deepest (longest waiting) at what position
    //
    // Returns the customer at the end of the queue
    // -------------------------------------------------------
    public Customer findLongestWaiting() {
        if (isEmpty()) return null;
        return findLongestHelper(head, 1);
    }

    private Customer findLongestHelper(Node current, int position) {
        // base case: Reached the last node (no next)
        if (current.getNext() == null) {
            System.out.println("(Reached bottom of queue at position " + position + " — starting way back)");
            return current.getData();
        }
        // recursive call: Go deeper first
        Customer result = findLongestHelper(current.getNext(), position + 1);
        // Way back: Just pass the result up, no additional processing
        return result;
    }

    // VIEW HISTORY: Display all customers with their status

    public void displayHistory() {
        System.out.println("\n=== Customer History ===");
        if (history.isEmpty() && isEmpty()) {
            System.out.println("No customer records");
            return;
        }
        
        if (!history.isEmpty()) {
            System.out.println("\n[CALLED CUSTOMERS]");
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + ". " + history.get(i).toStringWithStatus());
            }
        }
        
        if (!isEmpty()) {
            System.out.println("\n[WAITING IN QUEUE]");
            Node current = head;
            int position = 1;
            while (current != null) {
                System.out.println((history.size() + position) + ". " + current.getData().toStringWithStatus());
                current = current.getNext();
                position++;
            }
        }
    }

    // Utility
    public boolean isEmpty() { return head == null; }
    public int getSize()     { return size; }
}
