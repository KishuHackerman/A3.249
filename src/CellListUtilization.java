import java.io.*;
import java.util.Scanner;

public class CellListUtilization {

    public static void main(String[] args) {
        // Step (a): Create at least two empty lists
        CellList list1 = new CellList();
        CellList list2 = new CellList(); // Reserved for further testing

        // Step (b): Read the file and populate list1, ensuring no duplicates
        try (BufferedReader br = new BufferedReader(new FileReader("Cell_Info.txt"))) {
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
                        System.out.println("Parsed Phone: SerialNum = " + serialNum + ", Brand = " + brand + ", Price = " + price + ", Year = " + year);

                        // Create a new CellPhone object
                        CellPhone newPhone = new CellPhone(serialNum, brand, year, price);

                        // Check for duplicates using the contains method
                        if (!list1.contains(serialNum)) {
                            list1.addToStart(newPhone);
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
        System.out.println("Contents of list1:");
        list1.showContents();

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

        // Test other methods here if needed (Step e)
        // Example: list2.addToStart(new CellPhone(1234567, "TestBrand", 2023, 499.99));
        scanner.close();
    }
}



