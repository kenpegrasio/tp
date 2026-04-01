# Developer Guide

## Acknowledgements

- AddressBook-Level2 (https://github.com/se-edu/addressbook-level2) — referenced for project structure and command pattern design.

## Design & Implementation

### Architecture

InventoryBRO follows a command-based architecture. When the user types a command, `Parser` identifies it and returns the appropriate `Command` object, which is then executed against the `ItemList`.
```
User Input → InventoryBro → Parser → Command → ItemList
                                          ↓
                                          Ui
```

### Edit Feature

The edit feature allows users to update an existing item's name and quantity.

**Implementation:** `EditCommand` parses the input in the format `edit INDEX d/NEW_NAME q/NEW_QUANTITY`. It retrieves the item at the given index from `ItemList`, then calls `setDescription()` and `setQuantity()` on it.

**Example usage:** `edit 1 d/Apple q/50`

**Design consideration:** The command throws `IllegalArgumentException` for invalid index or non-numeric input, which is caught by `InventoryBro` and displayed via `Ui`.

### Price Feature

The price feature allows users to set a price for an existing item.

**Implementation:** `PriceCommand` parses the input in the format `setPrice INDEX p/PRICE`. It retrieves the item at the given index and calls `setPrice()` on it. Price is stored as a `double` in the `Item` class and displayed when listing items.

**Example usage:** `setPrice 1 p/3.50`

**Design consideration:** Price defaults to `0.0` when an item is first created. Negative prices are rejected with an appropriate error message.

## Product Scope

### Target User Profile

Small business owners or store managers who prefer a fast, command-line interface to manage their inventory.

### Value Proposition

InventoryBRO provides a lightweight, keyboard-driven inventory management tool that is faster than traditional GUI apps for users who are comfortable with CLI.

## User Stories

| Version | As a ...       | I want to ...                        | So that I can ...                            |
|---------|----------------|--------------------------------------|----------------------------------------------|
| v1.0    | store manager  | add items to my inventory            | keep track of my stock                       |
| v1.0    | store manager  | delete items from my inventory       | remove discontinued products                 |
| v1.0    | store manager  | edit an item's name and quantity     | correct mistakes or update stock             |
| v1.0    | store manager  | list all items                       | see my current inventory at a glance         |
| v2.0    | store manager  | set a price for each item            | track the value of my inventory              |
| v2.0    | store manager  | record sales and restocks            | keep my quantity counts accurate             |

## Non-Functional Requirements

- Should work on Windows, macOS, and Linux with Java 17 installed.
- Should respond to any command within 1 second for inventory sizes up to 1000 items.
- Data should persist between sessions via a save file.

## Glossary

* **Item** - A product in the inventory with a name, quantity, and price.
* **Index** - The 1-based position of an item in the inventory list.
* **Transaction** - A change in quantity, either positive (restock) or negative (sale).

## Instructions for Manual Testing

1. Launch the app with `java -jar InventoryBro.jar`
2. Add an item: `addItem d/Apple q/10`
3. Edit the item: `edit 1 d/Green Apple q/20`
4. Set a price: `setPrice 1 p/1.50`
5. List items: `listItems`
6. Delete an item: `deleteItem 1`
7. Exit: `exit`