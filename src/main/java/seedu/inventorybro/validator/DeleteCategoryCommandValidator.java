package seedu.inventorybro.validator;

import seedu.inventorybro.CategoryList;
import seedu.inventorybro.ItemList;

public class DeleteCategoryCommandValidator implements Validator {
    private final String input;

    public DeleteCategoryCommandValidator(String input) {
        this.input = input;
    }

    @Override
    public void validate(ItemList items, CategoryList categories) {
        if (!input.startsWith("deleteCategory ")) {
            throw new IllegalArgumentException("Invalid format. Use: deleteCategory c/CATEGORY_NAME");
        }

        String[] parts = input.split(" c/", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid format. Use: deleteCategory c/CATEGORY_NAME");
        }

        String targetCategory = parts[1].trim();

        if (targetCategory.equalsIgnoreCase("Others")) {
            throw new IllegalArgumentException(
                    "The [OTHERS] category is the default system category and cannot be deleted.");
        }

        if (!categories.containsCategory(targetCategory)) {
            throw new IllegalArgumentException(
                    "The category [" + targetCategory.toUpperCase() + "] does not exist.");
        }
    }
}
