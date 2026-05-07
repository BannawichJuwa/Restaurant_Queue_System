public class Customer {
    private int id;
    private String name;
    private int tableSize;
    private boolean called;  // Status: called or not

    public Customer(int id, String name, int tableSize) {
        this.id = id;
        this.name = name;
        this.tableSize = tableSize;
        this.called = false;  // Initially not called
    }

    public int getId()        { return id; }
    public String getName()   { return name; }
    public int getTableSize() { return tableSize; }
    public boolean isCalled() { return called; }
    public void setCalled(boolean called) { this.called = called; }

    public String toStringWithStatus() {
        String status = called ? "[CALLED]" : "[WAITING]";
        return "[#" + id + "] " + name + " (Table: " + tableSize + " people) " + status;
    }

    @Override
    public String toString() {
        return "[#" + id + "] " + name + " (Table: " + tableSize + " people)";
    }
}
