# Project Portfolio Page - Adit

## Project: InventoryBro

### Overview

InventoryBro is an inventory management tracking application interacted via a CLI. It is designed for mini-mart or 
nook and corner shopowners who are looking to have a way to manage and track their inventory of goods that they sell. 
It is written in Java.

### Summary of Contributions

* Code contributed: [RepoSense Link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=adbsw&breakdown=true)
* Features implemented: 
  * Added the ability to view a list of all inventory items in the chronological order that the items are added, or view a list of all inventory items sorted by a desired property and order specified by the users.
    * Justification: This allows shopowners to view their inventory of items all at once, with the ease of sorting it (e.g. by the quantities of goods) so that they can gain more information about the goods (e.g goods that are low on stock, goods that priced high) and it cmparison with other goods at a glance.
  * Added the ability to view summaries of all commands that users can use in the application, or view detailed instructions of a particular command specified by the users.
    * Justification: This allows new users of the application to learn about the commands and their functions before getting started with their inventory management, as well as reminding themselves of the commands along the way of using the application.
* Enhancements to code:
  * Updated ItemList to have a method for sorting the list of items by a property and order and returning the sorted list.
* Contributions: Updating UML diagrams and sections of the User and Developer guides not specific to a feature (Overall architecture diagram, editing of UML diagram to follow standard conventions, updating of user stories in the Developer Guide)
* Contributions to the USer Guide:
  * Sections:
    * Added: Feature List (v2.0) - Viewing All Items: `listItems`, Feature List (v2.0) - Getting Help: `help`
  * Sections updated:
    * Command Summary 
* Contributions to the Developer Guide:
  * Sections:
    * Added: Implementation - Viewing list of items in the inventory, Implementation - Viewing help messages of commands
    * Updated: Product scope - User Stories
  * UML diagrams:
    * Contributed:
      * [Overall Architecture/Class Diagram](../diagrams/InventoryBroClassDiagram.png)
    * Added: ListCommand class diagram, ListCommand sequence diagram, HelpCommand class diagram, HelpCommand sequence diagram
      * [ListCommandClassDiagram](../diagrams/ListCommandClassDiagram.png)
      * [ListCommandSequenceDiagram](../diagrams/ListCommandSequenceDiagram.png)
      * [HelpCommandClassDiagram](../diagrams/HelpCommandClassDiagram.png)
      * [HelpCommandSequenceDiagram](../diagrams/HelpCommandSequenceDiagram.png)
  