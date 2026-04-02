package seedu.inventorybro.validator;

import seedu.inventorybro.ItemList;

//@@author elliotjohnwu
/**
 * Validates the raw input string for the showTransactionHistory command.
 */
public class ShowTransactionHistoryValidator implements Validator {
    private final String input;

    public ShowTransactionHistoryValidator(String input) {
        assert input != null : "Input line should not be null";
        this.input = input;
    }

    @Override
    public void validate(ItemList items) {
        String[] words = input.split(" ");
        if (!words[0].equals("showHistory") || words.length > 1) {
            throw new IllegalArgumentException("Did you mean 'showHistory'?");
        }
    }
}
