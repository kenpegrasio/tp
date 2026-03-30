package seedu.inventorybro.command;

import seedu.inventorybro.Item;
import seedu.inventorybro.ItemList;
import seedu.inventorybro.Ui;

/**
 * Finds and lists all items in the inventory whose description contains the keyword.
 */
public class FindCommand implements Command {
    private final String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(ItemList items, Ui ui) {
        String[] words = input.split(" ", 2);

        // Ensure the user actually typed a keyword to search for
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid find format! Use: find KEYWORD");
        }

        String keyword = words[1].trim().toLowerCase();
        boolean isFound = false;

        ui.showMessage("Here are the matching items in your inventory:");

        for (int i = 0; i < items.size(); i++) {
            Item item = items.getItem(i);

            if (item.getDescription().toLowerCase().contains(keyword)) {
                ui.showMessage((i + 1) + ". " + item);
                isFound = true;
            }
        }

        if (!isFound) {
            ui.showMessage("No matching items found for: " + words[1].trim());
        }
    }
}
