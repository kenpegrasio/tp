package seedu.inventorybro;

import java.util.Scanner;

public class InventoryBro {

    /**
     * Main entry-point for the java.seedu.inventorybro.InventoryBro application.
     */
    public static void main(String[] args) {
        System.out.println("InventoryBRO");
        System.out.println("Enter something:");
        Scanner in = new Scanner(System.in);
        System.out.println("Test: " + in.nextLine());
    }
}
