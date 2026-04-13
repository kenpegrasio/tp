package seedu.inventorybro.command;

import seedu.inventorybro.Category;
import seedu.inventorybro.CategoryList;
import seedu.inventorybro.ItemList;
import seedu.inventorybro.Ui;
import seedu.inventorybro.validator.DeleteCategoryCommandValidator;

public class DeleteCategoryCommand implements Command {
    private final String input;

    public DeleteCategoryCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(ItemList items, CategoryList categories, Ui ui) {
        new DeleteCategoryCommandValidator(input).validate(items, categories);

        String targetCategoryName = input.split(" c/", 2)[1].trim();
        Category othersCategory = categories.getCategory("Others");

        // 1. Reassign orphaned items to [OTHERS]
        int reassignedCount = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.getItem(i).getCategory().getName().equalsIgnoreCase(targetCategoryName)) {
                items.getItem(i).setCategory(othersCategory);
                reassignedCount++;
            }
        }

        // 2. Delete the category
        categories.removeCategory(targetCategoryName);

        ui.showMessage("Successfully deleted the [" + targetCategoryName.toUpperCase() + "] category.");
        if (reassignedCount > 0) {
            ui.showMessage("Moved " + reassignedCount + " orphaned item(s) back into the [OTHERS] category.");
        }
    }
}
