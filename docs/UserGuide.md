# User Guide

## Introduction

InventoryBRO is a command-line inventory management app for small business owners who prefer typing over clicking. It helps you track your items, quantities, and prices quickly and efficiently.

## Quick Start

1. Ensure you have Java 17 or above installed.
2. Download the latest `InventoryBro.jar` from the releases page.
3. Open a terminal and navigate to the folder containing the jar file.
4. Run: `java -jar InventoryBro.jar`

## Features

### Add an item: `addItem`
Adds a new item to the inventory.

Format: `addItem d/NAME q/QUANTITY`

Example: `addItem d/Apple q/10`

---

### List all items: `listItems`
Shows all items currently in the inventory.

Format: `listItems`

---

### Edit an item: `edit`
Updates an existing item's name and quantity.

Format: `edit INDEX d/NEW_NAME q/NEW_QUANTITY`

* `INDEX` is the item number shown in `listItems` (starts from 1).

Example: `edit 1 d/Green Apple q/20`

---

### Set item price: `setPrice`
Sets the price of an existing item.

Format: `setPrice INDEX p/PRICE`

* `PRICE` must be a non-negative number.

Example: `setPrice 1 p/1.50`

---

### Record a transaction: `transact`
Adjusts an item's quantity by a positive or negative amount.

Format: `transact INDEX q/CHANGE`

* Use a negative number for sales, positive for restocks.

Example: `transact 1 q/-3`

---

### Delete an item: `deleteItem`
Removes an item from the inventory.

Format: `deleteItem INDEX`

Example: `deleteItem 1`

---

### Exit: `exit`
Exits the application.

Format: `exit`

---

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Copy the save file generated in the same folder as the jar file to your new computer.

## Command Summary

| Command    | Format                                  |
|------------|-----------------------------------------|
| Add item   | `addItem d/NAME q/QUANTITY`             |
| List items | `listItems`                             |
| Edit item  | `edit INDEX d/NEW_NAME q/NEW_QUANTITY`  |
| Set price  | `setPrice INDEX p/PRICE`               |
| Transact   | `transact INDEX q/CHANGE`              |
| Delete     | `deleteItem INDEX`                      |
| Exit       | `exit`                                  |