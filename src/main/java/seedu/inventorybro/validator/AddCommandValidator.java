package seedu.inventorybro.validator;

import java.util.regex.Pattern;

import seedu.inventorybro.ItemList;

//@@author kenpegrasio
/**
 * Validates the raw input string for the addItem command.
 */
public class AddCommandValidator implements Validator {
    private static final Pattern ADD_COMMAND_PATTERN = Pattern.compile("^addItem d/(.*?) q/(\\d+)$");
    private final String input;

    /**
     * Creates a validator bound to the given raw input string.
     *
     * @param input The full addItem command string to validate.
     */
    public AddCommandValidator(String input) {
        assert input != null : "Input should not be null";
        this.input = input;
    }

    /**
     * Checks that the input matches the {@code addItem d/NAME q/INITIAL_QUANTITY} pattern.
     *
     * @param items Unused; present to satisfy the {@link Validator} contract.
     * @throws IllegalArgumentException if the input does not conform to the expected format.
     */
    @Override
    public void validate(ItemList items) {
        assert input != null : "Input should not be null";
        if (!ADD_COMMAND_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(
                    "Invalid addItem format! Use: addItem d/NAME q/INITIAL_QUANTITY"
            );
        }
    }
}
