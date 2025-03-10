1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples). 

To realize this requirement, I added to the design a class GroceryList with attributes list in type List<MyItem> and name. MyItem is a class with Item, quantity and marked attributes. Item is a class with type and name attributes. 
To support add item functionality, GroceryList list class contains addItem method that received MyItem object and adds to the list. If MyItem already present in the list, quantity is added to existing one. So, list doesn’t contain duplicate MyItem objects.
To support delete item functionality, GroceryList list class contains deleteItem and deleteItemByIndex methods. First one received MyItem object and removes from the list. Since MyItem class has equals method which checks equality by Item, we can remove from list by MyItem object. We can also delete from the list by index as well using deleteItemByIndex method.
To change the quantity of items in the list, we can use getItem and then use quantity setter to change the quantity of any item.  getItem method receives the index of item in the list.


2. The application must contain a database (DB) of items and corresponding item types. 

To realize this requirement, I added to the design a class MyItem with attributes type and name. We can create big list of Item objects so that user can choose items from this list and we use this list as menu.

3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item. 
We create big list of Item object which serves as menu. Since Item implements Comparable interface which compares Item objects by type first and then name, we can sort the list so that all items belonging to same type will be shown grouped. After choosing Item object, we can create MyItem object by passing Item and quantity.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB. 
Not considered because it does not affect the design directly.

5. Lists must be saved automatically and immediately after they are modified. 
Not considered because it does not affect the design directly. We can save after each modification to database. The name of Item is unique, so it can be used as ID in the database.

6. Users must be able to check off items in a list (without deleting them). 
To realize this requirement, I added marked attribute to MyItem. We can use setter method to mark and unmark or see current state using getter method.

7. Users must also be able to clear all the check-off marks in a list at once. 
To realize this requirement, I added clearMarks method to GroceryList class.

8. Check-off marks for a list are persistent and must also be saved immediately. 
Not considered because it does not affect the design directly.

9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of product at once (i.e., without having to go back and forth between aisles). 
To realize this requirement, I implemented Comparable interface to Item class which compares Item objects by type first and then by name. Sorting list gives us grouped items.

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists. 
To realize this requirement, I added to the design a class GroceryLists with attribute list in type List<GroceryList>.
addList(GroceryList) method of GroceryLists class allows us to add new GroceryList. 
deleteList(GroceryList) method of GroceryLists class allows us to delete existing GroceryList from the list. We can check equality GroceryList objects by name as equals method is implemented in GroceryList class.
deleteListByIndex(index) method of GroceryLists class allows us to delete existing GroceryList from the list by index. 
To rename GroceryList list, I added setter for name attribute of GroceryList class.


11. The User Interface (UI) must be intuitive and responsive.
Not considered because it does not affect the design directly.

