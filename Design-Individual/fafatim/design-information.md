1.A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).

=> In the Shopping Card class user can add a list by calling addToList() method. And in the Shopping List class user can edit quantity by calling editList() method.

2. The application must contain a database (DB) of items and corresponding item types.

=>Implemented but not modeled as its not part of UML guidelines, a database of items and corresponding item types

3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.

=>When the user goes to invoke the addCategory() method there is a class called Category that has a list of predefined category in which the user can choose from, at which point there's another class called Product that has a list of predefined products.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.

=> Not fully displayed as it's a UI feature more than a UML but in the listView class there is a method called searchProduct() in which a user can search for a product by directly querying the database across multiple lists. The addNewProduct() method also lets users add product category and product in AddNewProduct class.


5. Lists must be saved automatically and immediately after they are modified.

=>Once a product is created the Product class will immediately update the database.

6. Users must be able to check off items in a list (without deleting them).

=>In the ShoppingList class user can check off items in a list by calling editList() method.

7. Users must also be able to clear all the check-off marks in a list at once.

=> In the ShoppingCart and ShoppingList class user can clear off items in a list by calling deleteList() method.

8. Check-off marks for a list are persistent and must also be saved immediately.

=>Once a Shopping Cart and Shopping List class is called, it will immediately update the database

9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of product at once (i.e., without having to go back and forth between aisles).


=>Since this is a UI element it won't fully reflect in the UML but the Shopping Cart and Shopping List class allow user to shop for a specific type of product at once.

The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.

=> In the Shopping cart class user can create different type list and store in the Shopping list class, where user can edit list anytime.

The User Interface (UI) must be intuitive and responsive.
=>According to UML the User Interface (UI) is enough intuitive and responsive.
