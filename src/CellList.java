// -----------------------------------------------------
// Assignment 3
// Written by: Ayush Patel (40285846) and Krishna Patel (40200870)
// -----------------------------------------------------

import java.util.NoSuchElementException;

public class CellList {
    private CellNode head;
    private int size;

        public class CellNode {
            private CellPhone phone;
            private CellNode next;

            public CellNode() {
                this.phone = null;
                this.next = null;
            }

            public CellNode(CellPhone phone, CellNode next) {
                this.phone = phone;
                this.next = next;
            }

            public CellNode(CellNode other) {
                this.phone = other.phone;
                this.next = other.next;
            }

            public CellNode(CellPhone phone) {
                this.phone = phone;
            }

            public CellNode clone() {
                return new CellNode(this);
            }

            public CellPhone getCellPhone() {
                return phone;
            }

            public void setCellPhone(CellPhone phone) {
                this.phone = phone;
            }

            public CellNode getNext() {
                return next;
            }

            public void setNext(CellNode next) {
                this.next = next;
            }

        }

    public CellList() {
            this.head = null;
            this.size = 0;
    }

    public CellNode getFirstNode() {
        return head;  // Returns the first node (head) of the list
    }

    public CellList(CellList other){
            if(other == null)
                return;
            this.head = copyList(other.head);
            this.size = other.size;
    }

    private CellNode copyList(CellNode node) {
            if (node == null)
                return null;
            return new CellNode(node.getCellPhone(),copyList(node.getNext()));
    }

    public void addToStart(CellPhone phone) {
        System.out.println("Adding phone: " + phone);  // Print to confirm addition
        head = new CellNode(phone, head);
        size++;
    }

    // Add to the end of the list
    public void addToEnd(CellPhone phone) {
        CellNode newNode = new CellNode(phone);  // Create a new node with the phone
        if (head == null) {
            head = newNode;  // If the list is empty, make the new node the head
        } else {
            CellNode current = head;
            // Traverse to the last node
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);  // Set the next of the last node to the new node
        }
    }



    // Insert at Index
    public void insertAtIndex(CellPhone phone, int index) {
       if (index < 0 || index > size){
           throw new NoSuchElementException("Invalid index: " + index);
       }
       if (index == 0){
           addToStart(phone);
           return;
       }
       CellNode current = head;
       for(int i = 0; i < index-1; i++){
           current = current.getNext();
       }
       current.setNext(new CellNode(phone,current.getNext()));
       size++;
    }

    // Delete from Index
    public void deleteFromIndex(int index){
            if(index < 0 || index > size){
                throw new NoSuchElementException("Invalid index: " + index);
            }
            if(index == 0){
                deleteFromStart();
                return;
            }

            CellNode current = head;
            for(int i = 0; i < index-1; i++){
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
            size--;
    }
    public void deleteFromStart(){
            if(head == null) {
                return;
            }
                head = head.getNext();
                size--;

    }

    // Replace at Index
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



    // Contains
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


    // Show Contents
    public void showContents() {
        CellNode current = head;
        System.out.println("The list contains:");
        while (current != null) {
            System.out.print(current.getCellPhone() + " -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

    // Equals
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
        return current; // Privacy leak!
    }

    public void printList() {
        CellNode current = head;  // Start at the head node

        while (current != null) {
            System.out.println(current.getCellPhone());  // Print the phone data from the current node
            current = current.getNext();  // Move to the next node
        }
    }


}

