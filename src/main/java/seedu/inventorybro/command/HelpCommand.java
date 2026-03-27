package seedu.inventorybro.command;

import seedu.inventorybro.ItemList;
import seedu.inventorybro.Ui;

/**
 * Displays to the user the names and summaries of each command of the application or the detailed
 * instruction of the command specified in the input.
 */
public class HelpCommand implements Command {
    private final String input;

    /**
     * Creates a help command from the raw user input.
     *
     * @param input The full help command string.
     */
    public HelpCommand(String input) {
        assert input != null : "Input line should not be null";
        this.input = input;
    }

    /**
     * Validates the help command input and prints the summaries of each command to the user, or
     * prints the detailed instruction of the command specified in the input.
     *
     * @param items The inventory item list to operate on.
     * @param ui    The ui object.
     */
    @Override
    public void execute(ItemList items, Ui ui) {
        String[] words = input.split(" ");

        if (!words[0].equals("help")) {
            throw new IllegalArgumentException("Did you mean 'help'?");
        }

        String info = null;
        if (words.length == 1) {
            info = helpSummaryCommand();

            ui.showMessage(info);

            return;
        }

        boolean isNotCommand = !words[1].equals("addItem") && !words[1].equals("deleteItem")
                && !words[1].equals("editItem") && !words[1].equals("transact")
                && !words[1].equals("listItems") && !words[1].equals("help")
                && !words[1].equals("exit");

        if (isNotCommand || !(words.length == 2)) {
            throw new IllegalArgumentException("Invalid help format. "
                    + "Use: help [VALID_COMMAND_NAME]"
                    + System.lineSeparator()
                    + "or enter 'help' to display each command name and their summaries.");
        }

        switch (words[1]) {
            case "addItem":
                info = helpAddItemCommand();
                break;
            case "deleteItem":
                info = helpDeleteItemCommand();
                break;
            case "editItem":
                info = helpEditItemCommand();
                break;
            case "transact":
                info = helpTransactCommand();
                break;
            case "listItems":
                info = helpListItemsCommand();
                break;
            case "help":
                info = helpHelpCommand();
                break;
            case "exit":
                info = helpExitCommand();
                break;
            default:
                break;
        }

        ui.showMessage(info);
    }

    /**
     * @return the command names and their summaries.
     */
    private static String helpSummaryCommand() {

        return """
                Command names and their summaries:
                addItem:    Adds a new item of a given quantity to the current inventory list.
                deleteItem: Deletes an item from the current inventory list.
                editItem:   Edits the name and/or quantity of an existing item in the inventory
                            based on item index. At least name or quantity must be provided,
                            existing values are updated to the input values.
                transact:   Updates stock quantities after a sale or restock.
                listItems:  Displays all items currently in the inventory, or displays message
                            to the user that the inventory is empty if there are no items.
                help:       Displays summaries of each command to the user, or displays a detailed
                            instruction of a specified command.
                exit:       Closes the application.
                
                For further details on a particular command, specify it using 'help [COMMAND_NAME]'.
                """;
    }

    /**
     * @return the detailed instruction of the command 'addItem'.
     */
    private static String helpAddItemCommand() {

        return """
                addItem:
                Adds a new item of a given name and quantity to the current inventory list.
                
                Example usage: addItem d/Apples q/10
                This adds an item named 'Apples' of quantity '10' to the inventory list.
                """;
    }

    /**
     * @return the detailed instruction of the command 'deleteItem'.
     */
    private static String helpDeleteItemCommand() {
        return """
                deleteItem:
                Deletes an item from the current inventory list based on the provided list index.
                Enter 'listItems' to view the list index of the item you wish to delete.
                
                Example usage: deleteItem 1
                This removes the item indexed at 1 in the inventory list.
                """;
    }

    /**
     * @return the detailed instruction of the command 'editItem'.
     */
    private static String helpEditItemCommand() {
        return """
                editItem:
                Edits the name and/or quantity of an existing item in the current inventory list
                based on the provided list index. At least a name or quantity should be provided.
                Enter 'listItems' to view the list index of the item you wish to edit.
                
                Example usages:-
                (Both name and quantity fields provided): editItem 1 d/Oranges q/20
                This updates the name and quantity of the item indexed at 1 in the inventory list
                to 'Oranges' and '20' respectively.
                
                (Just name field provided): editItem 1 d/Oranges
                This updates only the name of the item indexed at 1 in the inventory list to
                'Oranges'.
                
                (Just quantity field provided): editItem 1 q/20
                This updates only the quantity of the item indexed at 1 in the inventory list to
                '20'.
                """;
    }

    /**
     * @return the detailed instruction of the command 'transact'.
     */
    private static String helpTransactCommand() {
        return """
                transact:
                Increases (restocking) or decreases (selling) quantity of the item based on the
                provided list index.
                
                To increase the quantity, simply key in the change in quantity.
                To decrease the quantity, precede the change in quantity with '-'.
                
                The existing quantity value will be updated by adding or subtracting the provided
                change in quantity. The quantity cannot be decreased below 0 or it will result in
                an error.
                
                Enter 'listItems' to view the list index of the item you wish to transact.
                
                Example usages:-
                (Restocking): transact 1 q/10
                This adds '10' to the existing quantity assigned to the item indexed at 1
                in the inventory list.
                
                (Selling): transact 1 q/-10
                This subtract off '10' from the existing quantity assigned to the item indexed at 1
                in the inventory list, provided that it does not result in a quantity lower than 0.
                Otherwise, an error will be shown.
                """;
    }

    /**
     * @return the detailed instruction of the command 'listItems'.
     */
    private static String helpListItemsCommand() {
        return """
                listItems:
                Displays the list of items and their quantities in the current inventory, or displays
                a message to the user that inventory is empty if there are no items in the current
                inventory.
                
                Example usage: listItems
                """;
    }

    /**
     * @return the detailed instruction of the command 'help'.
     */
    private static String helpHelpCommand() {
        return """
                help:
                Displays the command names of the application and their summaries, or a command can
                be specified to display a more detailed instruction of it. You can use 'help' to view
                the valid name of the command, and specify that command to view further details of
                it.
                
                Example usage:-
                (Without specifying a command): help
                This displays each command name and their summaries.
                
                (Specifying a command): help addItem
                This displays a more detailed instruction of how to use the command 'addItem'.
                """;
    }

    /**
     * @return the detailed instruction of the command 'exit'.
     */
    private static String helpExitCommand() {
        return """
                exit:
                Closes the application.
                
                Example usage: exit
                """;
    }
}
