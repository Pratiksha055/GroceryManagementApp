# GroceryListManager

1. A **grocery list** consists of **items** the **users** want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).

**For this requirement, a User class which contains user's first and last name  along with operations for retrieving, creating, modifying and deleting grocery lists and adding/removing items from the grocery list will be used.

A GroceryList class is also used to store grocery list items.  The class contains the name of the grocery list so multiple grocery lists may be created by a user.**


2. The application must contain a database (DB) of **items** and corresponding **item types**.
    
**To satisfy this requirement, an item class will be used along with an item types class.  Each item will be associated with my or more item types.**


3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.

**This requirement is satisfied by methods in the user class which allow the user to add an item to the grocery list and update the quantity for that item. The quantity attribute is part of the ItemStatus class.  Items can be added to the list by searching by ItemType.**

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.

**This requirement is satisfied through a method in the user class which allows user to search by item name in order to add an item to the grocery list.**


5. Lists must be saved automatically and immediately after they are modified.
This requirement is not considered  as it does not impact the application design.

6. Users must be able to check off items in a list (without deleting them).
User class contains a method that provides this functionality. The ItemStatus class contains isChecked boolean property.

7. Users must also be able to clear all the check-off marks in a list at once.
**ItemStatus class is used in order to fulfill this requirement.  The User class contains methods that allows the user to check off/uncheck one item or check/uncheck all items at once.**

8. Check-off marks for a list are persistent and must also be saved immediately.
Not considered as it has no impact on the application design.

9. The application must present the items in a list grouped by type, so as to allow users to
shop for a specific type of product at once (i.e., without having to go back and forth
between aisles).
**This requirement is implemented using the ItemType class**

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly
farmer’s market list”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete lists.
**The user class has methods for creating, deleting and modifying lists as well as getting the list and getting items (products) in a list. The GroceryList class can be named/renamed in order to support multiple lists  with different names.**
 
11. The User Interface (UI) must be intuitive and responsive.
Not considered as it has no impact on the application design. This requirement is UI/performance related and is subjective.  Would need more specific requirements.

