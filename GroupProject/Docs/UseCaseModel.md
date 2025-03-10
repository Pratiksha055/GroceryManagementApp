# GroceryListManager Use Cases:

## 1 Use Case Diagram


![GroceryListManagerUseCaseDiagram](https://user-images.githubusercontent.com/54966020/200071841-c102d441-da20-44be-98d4-853c6eb0deea.png)


## 2 Use Case Descriptions

**Requirement 1:** User can open application application for first time and have the option to create a new grocery list a new grocery list
- pre-condition - user can launch application and sees main screen with option to create grocery lists
- post-condition - newly created list name can be seen by user
- Scenario:  No lists currently exist.  User launches app and creates a new list, giving the list a name.  No items are on the list.

**Requirement 2:** To add items, user can search for an item by item name or by category
- pre: user has created a list and items exist in database with associated categories
- post:  item search returns a match
- Scenario: 
	- Normal: user searches by item name and finds a matching item, adds it to list
	- Normal: user searches by item category (eg, fruit) and finds matching item names, chooses one and adds it to the list
	- Alternate: user searches by item name and does not find a matching item
	- Alternate: user searches by item category and does not find any matching items

**Requirement 3:** User can add items to the grocery list
- pre: blank grocery list has been created and user’s search has returned match(es)
- post: items successfully added and are visible on the list
- Scenario:
	User searches for an item and clicks on item to add it to the list.  User then sees newly added item on the grocery list

**Requirement 4:** User can delete list 
- pre:  list has previously been created
- post: deleted list is no longer visible on users list of existing grocery lists
- Scenario:
	User clicks on a previously created list and clicks option to delete the list.  

**Requirement 5:** User can rename grocery list
- pre: user has previously created list which is visible in app
- post:  User’s can see new name for previously created list
- Scenario: 
	Normal:  User selects a list and clicks option to rename the list, then types in new name for list
	Alternate:  User selects a list and clicks option to rename list, then decides not to rename the list and cancels

**Requirement 6:** User can check off an item on grocery list
- pre: list has been created and list items are present on list
- post: list item is visible with a checkmark next to the item
- Scenario: After adding items to the grocery list, user has purchased an item.  In order to mark the item as completed, they check the item off.

**Requirement 7:** User can uncheck all items from grocery list
- pre:  list is present and at least one item has been added to list and checked off
- post:  all items on list are unchecked
- Scenario: User has checked one or more items on the list and clicks uncheck all in order to remove all check marks

**Requirement 8:** User can add multiple grocery lists with different names
- pre:  one list has been created previously
- post:  multiple grocery lists are present with different names
- Scenario: Users sometimes may wish to create multiple separate grocery lists with different items or for different shopping trips.  

**Requirement 9:** User can exit out of application and list is saved automatically
- pre:  user has launched application and created a list with items added to it
- post:  user relaunches application a subsequent time and can see the previously created list with items that were previously added to it
- Scenario: User has created a shopping list and then closes out of the application.  When re-opening, the application, the user can pick up where they left off with the list.  The user should see all lists previously created with all items on each of the lists.  They should see all the items that have been checked off and can modify the existing lists (either adding/removing items or checking items off/unchecking items) as well as create additional lists.


