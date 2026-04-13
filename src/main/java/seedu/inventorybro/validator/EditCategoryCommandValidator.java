package seedu.inventorybro.validator;

import seedu.inventorybro.CategoryList;
import seedu.inventorybro.ItemList;

/**
 * Validates the raw input string for the editCategory command.
 */
public class EditCategoryCommandValidator implements Validator {

    private final String input;

    public EditCategoryCommandValidator(String input) {
        this.input = input;
    }

    @Override
    public void validate(ItemList items, CategoryList categories) {
        if (!input.startsWith("editCategory ")) {
            throw new IllegalArgumentException("Invalid editCategory format. Use: editCategory INDEX c/NEW_CATEGORY");
        }

        String[] parts = input.split(" c/", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid editCategory format. Use: editCategory INDEX c/NEW_CATEGORY");
        }

        String indexString = parts[0].substring("editCategory".length()).trim();
        int index;

        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Index must be a number.");
        }

        if (index <= 0 || index > items.size()) {
            throw new IllegalArgumentException("Invalid index.");
        }

        String newCategoryName = parts[1].trim();

        if (newCategoryName.isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty.");
        }

        // Must verify the category actually exists in the system before we move the item
        if (!categories.containsCategory(newCategoryName)) {
            throw new IllegalArgumentException(
                    "The category [" + newCategoryName.toUpperCase() + "] does not exist! " +
                            "Please create it first using: addCategory c/" + newCategoryName
            );
        }
    }
}
