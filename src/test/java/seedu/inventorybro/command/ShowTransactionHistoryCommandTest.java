package seedu.inventorybro.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.inventorybro.ItemList;
import seedu.inventorybro.Ui;
import seedu.inventorybro.storage.TransactionStorageStub;

//@@author elliotjohnwu
/**
 * Tests for {@link ShowTransactionHistoryCommand} using a stubbed storage.
 */
class ShowTransactionHistoryCommandTest {

    private ItemList items;
    private Ui ui;

    @BeforeEach
    void setUp() {
        items = new ItemList();
        ui = new Ui();
    }



    /**
     * Verifies empty history case.
     */
    @Test
    void executeValidCommandEmptyHistoryShowsEmptyMessage() {
        TransactionStorageStub stub =
                new TransactionStorageStub(new ArrayList<>());

        ShowTransactionHistoryCommand command =
                new ShowTransactionHistoryCommand("showHistory", stub);

        assertDoesNotThrow(() -> command.execute(items, ui));
    }

    /**
     * Verifies single entry case.
     */
    @Test
    void executeValidCommandOneEntryDisplaysEntry() {
        ArrayList<String> entries = new ArrayList<>();
        entries.add("Coke Can | -5 | 2026-03-26 14:30");

        TransactionStorageStub stub = new TransactionStorageStub(entries);

        ShowTransactionHistoryCommand command =
                new ShowTransactionHistoryCommand("showHistory", stub);

        assertDoesNotThrow(() -> command.execute(items, ui));
    }

    /**
     * Verifies multiple entries case.
     */
    @Test
    void executeValidCommandMultipleEntriesDisplaysAll() {
        ArrayList<String> entries = new ArrayList<>();
        entries.add("Coke Can | -5 | 2026-03-26 14:30");
        entries.add("Sprite Bottle | 10 | 2026-03-26 14:31");
        entries.add("Fanta | -3 | 2026-03-26 14:32");

        TransactionStorageStub stub = new TransactionStorageStub(entries);

        ShowTransactionHistoryCommand command =
                new ShowTransactionHistoryCommand("showHistory", stub);

        assertDoesNotThrow(() -> command.execute(items, ui));
    }


    @Test
    void execute_extraArguments_throwsException() {
        ShowTransactionHistoryCommand command =
                new ShowTransactionHistoryCommand(
                        "showHistory extra",
                        new TransactionStorageStub(new ArrayList<>())
                );

        assertThrows(IllegalArgumentException.class,
                () -> command.execute(items, ui));
    }

    @Test
    void execute_wrongCommandWord_throwsException() {
        ShowTransactionHistoryCommand command =
                new ShowTransactionHistoryCommand(
                        "history",
                        new TransactionStorageStub(new ArrayList<>())
                );

        assertThrows(IllegalArgumentException.class,
                () -> command.execute(items, ui));
    }

    @Test
    void execute_wrongCase_throwsException() {
        ShowTransactionHistoryCommand command =
                new ShowTransactionHistoryCommand(
                        "ShowHistory",
                        new TransactionStorageStub(new ArrayList<>())
                );

        assertThrows(IllegalArgumentException.class,
                () -> command.execute(items, ui));
    }
}
