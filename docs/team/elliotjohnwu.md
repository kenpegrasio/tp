# Wu Zi En Elliot John - Project Portfolio Page

## Overview

InventoryBRO is a CLI-based inventory management application for small business owners who prefer typing over mouse-driven UIs. It supports adding, editing, deleting, filtering, and transacting inventory items, with built-in tab-completion and typo detection to speed up daily use.

---

## Summary of Contributions

### Code Contributed

[View my code contributions on the tP Code Dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=elliotjohnwu)

---

### Enhancements Implemented

#### 1. Transaction Command (`transact`)

* **What it does:** Adjusts the quantity of an item using a signed integer (positive for restock, negative for sale) via the format `transact INDEX q/CHANGE`.
* **Justification:** Inventory systems require accurate tracking of stock movement. A dedicated transaction command ensures all quantity changes are explicit and recorded.
* **Highlights:**

    * Parses user input into item index and quantity change using controlled string splitting.
    * Updates item quantity while ensuring it does not become negative (validated beforehand).
    * Automatically records each transaction via `TransactionStorage`.
    * Integrates with `TransactCommandValidator` to enforce correct format and prevent invalid operations.
    * Logs operations using Java `Logger` for traceability.
* **Files:** `TransactCommand.java`, `TransactCommandValidator.java`

---

#### 2. Transaction History Feature (`showHistory`)

* **What it does:** Displays all past transactions stored in persistent storage.
* **Justification:** Users need visibility into past stock changes for auditing and tracking purposes.
* **Highlights:**

    * Loads transaction history from storage using `TransactionStorage`.
    * Gracefully handles empty history by displaying a user-friendly message.
    * Outputs indexed history entries for readability.
    * Supports dependency injection for testing via an alternate constructor.
* **Files:** `ShowTransactionHistoryCommand.java`, `ShowTransactionHistoryCommandValidator.java`

---

#### 3. Transaction Storage System

* **What it does:** Handles persistent storage of transaction history as formatted strings.
* **Justification:** Separating transaction storage from command logic improves modularity and maintainability.
* **Highlights:**

    * Stores entries in the format:

      ```
      itemName | quantityChange | timestamp
      ```
    * Automatically generates timestamps using `LocalDateTime`.
    * Extends a generic `Storage<String>` class to reuse file handling logic.
    * Includes validation in `decode()` to skip corrupted lines safely.
    * Logs warnings for malformed entries instead of crashing.
* **Files:** `TransactionStorage.java`

---

#### 4. Generic Storage Architecture (`Storage<T>`)

* **What it does:** Provides a reusable abstraction for file-based persistence across different data types.
* **Justification:** Avoids duplication of file I/O logic and standardises error handling across the system.
* **Highlights:**

    * Implements common operations:

        * `saveArray()` (overwrite)
        * `saveHistory()` (append)
        * `load()` (read with fault tolerance)
    * Uses abstract methods `encode()` and `decode()` for type-specific logic.
    * Automatically creates directories if they do not exist.
    * Integrates logging for both normal operations and failures.
* **Files:** `Storage.java`

---

#### 5. Inventory Storage (`ArrayStorage`)

* **What it does:** Handles saving and loading of `Item` objects.
* **Justification:** Provides persistence for the main inventory data while integrating with categories.
* **Highlights:**

    * Encodes items using `Item.toSaveFormat()`.
    * Decodes items from file with validation for:

        * Quantity
        * Price
        * Description
        * Category
    * Ensures category consistency:

        * Automatically creates missing categories during loading.
    * Skips corrupted lines without interrupting the load process.
* **Files:** `ArrayStorage.java`

---

#### 6. Test Stubs for Storage

* **What it does:** Provides isolated storage implementations for testing.
* **Justification:** Prevents tests from modifying real data and enables deterministic testing.
* **Highlights:**

    * `TransactionStorageStub` writes to a test-specific file path.
    * `TransactionStorageHistoryStub` returns predefined entries.
    * `ArrayStorageStub` redirects inventory storage to a test file.
    * Supports cleanup via test file deletion.
* **Files:**
  `TransactionStorageStub.java`,
  `TransactionStorageHistoryStub.java`,
  `ArrayStorageStub.java`

---

## Contributions to the Developer Guide (Extracts)

### Transaction Command — Design & Implementation

The `TransactCommand` is responsible for adjusting an item’s quantity and recording the change in persistent storage.

**Execution sequence:**

1. The user inputs a command in the format:

   ```
   transact INDEX q/CHANGE
   ```
2. `TransactCommandValidator` validates:

    * Command format
    * Index bounds
    * That resulting quantity is not negative
3. `TransactCommand.execute()` parses the input:

    * Splits input into index and quantity change
    * Converts both values into integers
4. The target `Item` is retrieved from `ItemList`
5. The new quantity is computed:

   ```
   newQuantity = currentQuantity + change
   ```
6. The item is updated via `item.setQuantity(newQuantity)`
7. `TransactionStorage.saveHistory()` is called to persist the transaction
8. `Ui` displays confirmation to the user

**Design considerations:**

* Validation is separated from execution (SLAP principle)
* Quantity updates are centralized in the `Item` class
* Transaction recording is decoupled via `TransactionStorage`

---

### Transaction Storage — Design & Implementation

The `TransactionStorage` class manages persistence of transaction history.

**Save sequence:**

1. `saveHistory(itemName, change)` is called
2. A timestamp is generated using:

   ```
   LocalDateTime.now().format(FORMATTER)
   ```
3. A formatted string entry is created:

   ```
   itemName | change | timestamp
   ```
4. The entry is passed to `Storage.saveHistory()`
5. The entry is appended to the transactions file

**Load sequence:**

1. `load()` reads the file line by line
2. Each line is passed to `decode(line, lineNumber)`
3. `decode()`:

    * Splits the line using `" | "`
    * Validates that there are at least 3 parts
    * Ensures the quantity change is a valid integer
4. Valid entries are added to the result list
5. Corrupted entries are skipped with a logged warning

**Design considerations:**

* Transactions are stored as strings for simplicity
* Decoding is defensive to prevent crashes from malformed data
* Logging provides traceability for skipped entries

---

### Generic Storage Architecture — Design & Implementation

The `Storage<T>` class abstracts common file operations for all storage types.

**Responsibilities:**

* File creation and directory handling
* Writing data (overwrite and append modes)
* Reading data with corruption tolerance
* Logging operations and failures

**Save (overwrite) sequence:**

1. `saveArray(items)` is called
2. File is created if it does not exist
3. Each item is encoded via `encode(T item)`
4. Encoded strings are written line-by-line

**Save (append) sequence:**

1. `saveHistory(item)` is called
2. File is opened in append mode
3. Encoded item is written to the end of the file

**Load sequence:**

1. File is opened using `Scanner`
2. Each line is trimmed and checked for emptiness
3. `decode(line, lineNumber)` is called
4. Valid objects are added to the result list
5. Invalid lines return `null` and are skipped

**Design considerations:**

* Uses generics to support multiple data types
* Delegates encoding/decoding to subclasses
* Ensures consistent error handling across storage implementations

---

### Inventory Storage (`ArrayStorage`) — Design & Implementation

The `ArrayStorage` class extends `Storage<Item>` to handle inventory items.

**Decode sequence:**

1. A line is split into components:

   ```
   quantity | description | price | category
   ```
2. Values are parsed into appropriate types:

    * `int quantity`
    * `String description`
    * `double price`
    * `String categoryName`
3. Validation checks:

    * Description is not empty
    * Category exists or is created dynamically
4. A new `Item` object is constructed and returned

**Category handling:**

* Uses a shared `CategoryList` (`masterCategories`)
* Ensures all items reference a valid category object
* Automatically creates missing categories during load

**Design considerations:**

* Prevents duplicate category instances
* Ensures referential integrity between items and categories
* Skips corrupted lines without affecting valid data

---

## Team-Based Tasks

* Set up the base storage architecture (SLAP Principle) to use for different storage classes
* Established patterns for fault-tolerant file handling
* Enabled safe and isolated testing through stub implementations
* Set up the Team Github Repo and drafted UserGuide v1.0
