package seedu.inventorybro;

import java.util.Scanner;

public class InventoryBro {

    /**
     * Main entry-point for the java.seedu.inventorybro.InventoryBro application.
     */
    public static void main(String[] args) {
        System.out.println("InventoryBRO");
        Scanner in = new Scanner(System.in);
        ItemList items = new ItemList();

        while (in.hasNextLine() {
            String input = in.nextLine();
            Parser.parse(input, items);
        }
    }
}
