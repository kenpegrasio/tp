package seedu.inventorybro.validator;

import seedu.inventorybro.CategoryList;
import seedu.inventorybro.ItemList;

public class ListCategoriesCommandValidator implements Validator {
    private final String input;

    public ListCategoriesCommandValidator(String input) {
        this.input = input;
    }

    @Override
    public void validate(ItemList items, CategoryList categories) {
        if (!input.trim().equalsIgnoreCase("listCategories")) {
            throw new IllegalArgumentException("Invalid format. Use: listCategories");
        }
    }
}
