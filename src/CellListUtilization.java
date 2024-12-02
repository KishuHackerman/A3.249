// -----------------------------------------------------
// Assignment 3
// Written by: Ayush Patel (40285846) and Krishna Patel (40200870)
// -----------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class CellListUtilization {

    public static void main(String[] args) {

        CellList list1 = new CellList();


        // Read the file and populate list1, ensuring no duplicates
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Kishu\\IdeaProjects\\A3 - 249\\src\\Cell_Info.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into parts
                String[] parts = line.trim().split("\\s+"); // Split by whitespace and trim
                if (parts.length == 4) {
                    try {
                        long serialNum = Long.parseLong(parts[0].trim());
                        String brand = parts[1].trim();
                        double price = Double.parseDouble(parts[2].trim());
                        int year = Integer.parseInt(parts[3].trim());

                        // Print values for debugging
                        System.out.println("Phone: SerialNum = " + serialNum + ", Brand = " + brand + ", Price = " + price + ", Year = " + year);

                        // Create a new CellPhone object
                        CellPhone newPhone = new CellPhone(serialNum, brand, price, year);

                        // Check for duplicates using the contains method
                        if (!list1.contains(serialNum)) {
                            list1.addToEnd(newPhone);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid data format on line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Step (c): Show the contents of the list
        System.out.println("\nContents of Cell_Info.txt:");

        // Assuming list1 uses a CellNode structure, iterate through the list manually
        CellList.CellNode currentNode = list1.getFirstNode();
        while (currentNode != null) {
            CellPhone phone = currentNode.getCellPhone(); // Assuming getCellPhone() retrieves the CellPhone object
            System.out.println("The serial number is: " + phone.getSerialNum());
            System.out.println("The brand is: " + phone.getBrand());
            System.out.println("The year is: " + phone.getYear());
            System.out.println("The price is: " + phone.getPrice());
            System.out.println("----------------------------");
            currentNode = currentNode.getNext(); // getNext() moves to the next node in the list
        }
        
        list1.addToEnd(new CellPhone(38909091, "Samsung", 987.28, 2022));
        list1.addToEnd(new CellPhone(27879852, "Acer", 572.2, 2013));
        list1.addToEnd(new CellPhone(49000883, "LG", 232.99, 2008));
        list1.addToEnd(new CellPhone(19890004, "Nokia", 237.24, 2006));
        list1.addToEnd(new CellPhone(890765, "Sharp", 564.22, 2009));

        System.out.println("Contents of list1:");
        list1.printList();
        System.out.println("\n");
        // Now copy list1 into list2 using the copy constructor
        CellList list2 = new CellList(list1);

        // Optionally, print the contents of list2 to verify the copy
        System.out.println("Contents of list2 (copy of list1):");
        list2.printList(); // Displays the copy constructor
        System.out.println("\n");

        // Step (d): Prompt the user to enter serial numbers for search
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter serial numbers to search (type -1 to stop):");

        while (true) {
            System.out.print("Enter serial number: ");
            long serialNum = scanner.nextLong();

            if (serialNum == -1) {
                break; // Exit loop on sentinel value
            }

            // Search for the serial number in the list
            CellList.CellNode foundNode = list1.find(serialNum);

            if (foundNode != null) {
                System.out.println("CellPhone found:\n" + foundNode.getCellPhone());
            } else {
                System.out.println("No CellPhone with serial number " + serialNum + " found.");
            }
        }


        CellPhone newPhone = new CellPhone(12345678, "OnePlus", 799.99, 2023);
        list1.insertAtIndex(newPhone, 2);  // Insert OnePlus phone at index 2
        System.out.println("\nContents of list1 after inserting OnePlus at index 2:");
        list1.printList();

        // Delete a phone at index 3
        list1.deleteFromIndex(3);  // Delete the phone at index 3
        System.out.println("\nContents of list1 after deleting phone at index 3:");
        list1.printList();


        scanner.close();
    }
}




