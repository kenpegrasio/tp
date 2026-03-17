package seedu.inventorybro;

import java.util.NoSuchElementException;

public class InventoryBro {
    private Ui ui;
    private ItemList items;

    public InventoryBro() {
        ui = new Ui();
        items = new ItemList();
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            String fullCommand = "";

            try {
                fullCommand = ui.readCommand();
            } catch (NoSuchElementException e) {
                ui.showError("No line found");
            }

            if (fullCommand.isEmpty()) {
                continue;
            }

            ui.showLine();
            try {
                // Pass the ui object into the parser so the commands can use it to print!
                Parser.parse(fullCommand, items, ui);
            } catch (IllegalArgumentException e) {
                // Catches all the exceptions thrown by your various Commands!
                ui.showError(e.getMessage());
            }
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new InventoryBro().run();
    }
}
