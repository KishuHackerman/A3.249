// -----------------------------------------------------
// Assignment 3
// Written by: Ayush Patel (40285846) and Krishna Patel (40200870)
// -----------------------------------------------------
import java.util.NoSuchElementException;

/**
 * The CellList class represents a linked list to store CellPhone objects.
 * It includes various methods for adding, removing, and modifying nodes within the list.
 */
public class CellList {
    private CellNode head;
    private int size;

    /**
     * Nested class representing a node in the CellList.
     * Each node contains a CellPhone object and a reference to the next node.
     */
    public class CellNode {
        private CellPhone phone;
        private CellNode next;

        /**
         * Default constructor for CellNode.
         * Initializes the phone and next references to null.
         */
        public CellNode() {
            this.phone = null;
            this.next = null;
        }

        /**
         * Parameterized constructor for CellNode.
         *
         * @param phone the CellPhone object to store in the node
         * @param next  the reference to the next node
         */
        public CellNode(CellPhone phone, CellNode next) {
            this.phone = phone;
            this.next = next;
        }

        /**
         * Copy constructor for CellNode.
         *
         * @param other the CellNode to copy from
         */
        public CellNode(CellNode other) {
            this.phone = other.phone;
            this.next = other.next;
        }

        /**
         * Parameterized constructor to create a node with only a CellPhone object.
         *
         * @param phone the CellPhone object to store in the node
         */
        public CellNode(CellPhone phone) {
            this.phone = phone;
        }

        /**
         * Clones the current CellNode.
         *
         * @return a new CellNode with the same data as the current node
         */
        public CellNode clone() {
            return new CellNode(this);
        }

        /**
         * Gets the CellPhone object stored in this node.
         *
         * @return the CellPhone object
         */
        public CellPhone getCellPhone() {
            return phone;
        }

        /**
         * Sets the CellPhone object for this node.
         *
         * @param phone the CellPhone object to set
         */
        public void setCellPhone(CellPhone phone) {
            this.phone = phone;
        }

        /**
         * Gets the next node in the list.
         *
         * @return the next CellNode
         */
        public CellNode getNext() {
            return next;
        }

        /**
         * Sets the reference to the next node.
         *
         * @param next the next CellNode to set
         */
        public void setNext(CellNode next) {
            this.next = next;
        }
    } // End of inner class

    /**
     * Default constructor for CellList.
     * Initializes the head node to null and the size to 0.
     */
    public CellList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Gets the size of the list.
     *
     * @return the number of nodes in the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the first node (head) of the list.
     *
     * @return the head node
     */
    public CellNode getFirstNode() {
        return head;
    }

    /**
     * Copy constructor for CellList.
     * Creates a deep copy of the provided list.
     *
     * @param other the CellList to copy from
     */
    public CellList(CellList other) {
        if (other == null) return;
        this.head = copyList(other.head);
        this.size = other.size;
    }

    /**
     * Creates a deep copy of the given node and its successors.
     *
     * @param node the starting node to copy
     * @return the head of the copied list
     */
    private CellNode copyList(CellNode node) {
        if (node == null) return null;
        return new CellNode(node.getCellPhone(), copyList(node.getNext()));
    }

    /**
     * Adds a new node with the given CellPhone to the start of the list.
     *
     * @param phone the CellPhone object to add
     */
    public void addToStart(CellPhone phone) {
        System.out.println("Adding phone: " + phone);
        head = new CellNode(phone, head);
        size++;
    }

    /**
     * Adds a new node with the given CellPhone to the end of the list.
     *
     * @param phone the CellPhone object to add
     */
    public void addToEnd(CellPhone phone) {
        CellNode newNode = new CellNode(phone);
        if (head == null) {
            head = newNode;
        } else {
            CellNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    /**
     * Inserts a new node with the given CellPhone at the specified index.
     *
     * @param phone the CellPhone object to add
     * @param index the position to insert the node at
     * @throws NoSuchElementException if the index is out of bounds
     */
    public void insertAtIndex(CellPhone phone, int index) {
        if (index < 0 || index > size) {
            throw new NoSuchElementException("Invalid index: " + index);
        }
        if (index == 0) {
            addToStart(phone);
            return;
        }
        CellNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        current.setNext(new CellNode(phone, current.getNext()));
        size++;
    }

    /**
     * Deletes the node at the specified index.
     *
     * @param index the position of the node to delete
     * @throws NoSuchElementException if the index is out of bounds
     */
    public void deleteFromIndex(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Invalid index: " + index);
        }
        if (index == 0) {
            deleteFromStart();
            return;
        }
        CellNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
        size--;
    }

    /**
     * Deletes the first node in the list.
     */
    public void deleteFromStart() {
        if (head == null) return;
        head = head.getNext();
        size--;
    }

    /**
     * Replaces the node at the specified index with a new CellPhone object.
     *
     * @param phone the CellPhone object to replace with
     * @param index the position of the node to replace
     */
    public void replaceAtIndex(CellPhone phone, int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index. Replacement skipped.");
            return;
        }
        CellNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setCellPhone(phone);
    }

    /**
     * Searches for a node with the specified serial number.
     *
     * @param serialNum the serial number to search for
     * @return the node containing the specified serial number, or null if not found
     */
    public CellNode find(long serialNum) {
        CellNode current = head;
        int iterations = 0;
        while (current != null) {
            iterations++;
            System.out.println("Checking phone with serial number: " + current.getCellPhone().getSerialNum());
            if (current.getCellPhone().getSerialNum() == serialNum) {
                System.out.println("Found after " + iterations + " iterations.");
                return current;
            }
            current = current.getNext();
        }
        System.out.println("Not found after " + iterations + " iterations.");
        return null;
    }

    /**
     * Checks if the list contains a node with the specified serial number.
     *
     * @param serialNum the serial number to check
     * @return true if the serial number is found, false otherwise
     */
    public boolean contains(long serialNum) {
        CellNode current = head;
        while (current != null) {
            if (current.getCellPhone().getSerialNum() == serialNum) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Displays the contents of the list.
     */
    public void showContents() {
        CellNode current = head;
        System.out.println("The list contains:");
        while (current != null) {
            System.out.println("The serial number is: " + current.getCellPhone().getSerialNum());
            System.out.println("The brand is: " + current.getCellPhone().getBrand());
            System.out.println("The year is: " + current.getCellPhone().getYear());
            System.out.println("The price is: " + current.getCellPhone().getPrice());
            current = current.getNext();
        }
    }

    /**
     * Compares this list with another for equality.
     *
     * @param obj the object to compare with
     * @return true if the lists are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CellList other = (CellList) obj;

        if (this.size != other.size) return false;

        CellNode current1 = this.head;
        CellNode current2 = other.head;

        while (current1 != null && current2 != null) {
            if (!current1.getCellPhone().equals(current2.getCellPhone())) {
                return false;
            }
            current1 = current1.getNext();
            current2 = current2.getNext();
        }
        return true;
    }

    /**
     * Privacy Leak Warning:
     * - This method may cause a privacy leak if a direct reference to a CellNode is returned.
     * - Reason: The caller can modify the internal structure of the list.
     * - Solution: Avoid returning direct references; instead, provide a copy.
     */
    public CellNode getNodeAt(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Invalid index: " + index);
        }
        CellNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return new CellNode(current.getCellPhone(), null); // Prevents privacy link by providing copy
    }

    /**
     * Prints the list by displaying the data of each node.
     */
    public void printList() {
        CellNode current = head;
        while (current != null) {
            System.out.println(current.getCellPhone());
            current = current.getNext();
        }
    }
}


