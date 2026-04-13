package seedu.inventorybro.command;

import seedu.inventorybro.Category;
import seedu.inventorybro.CategoryList;
import seedu.inventorybro.ItemList;
import seedu.inventorybro.Ui;
import seedu.inventorybro.validator.ListCategoriesCommandValidator;

public class ListCategoriesCommand implements Command {
    private final String input;

    public ListCategoriesCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(ItemList items, CategoryList categories, Ui ui) {
        new ListCategoriesCommandValidator(input).validate(items, categories);

        ui.showMessage("Here are your current categories:");
        int index = 1;
        for (Category category : categories.getCategories()) {
            ui.showMessage(index + ". [" + category.getName().toUpperCase() + "]");
            index++;
        }
    }
}
