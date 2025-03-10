#Test Plan 

**Author**: Team 5

## 1 Testing Strategy

### 1.1 Overall strategy

In this project we are following few test Strategy to make the error free quality application.

Unit testing: It is a type of testing in which individual units or functions of software testing. Its primary purpose is to test each unit or function. A unit is the smallest testable part of an application. It mainly has one or a few inputs and produces a single output. In procedural programming, a unit referred to as an individual program, while object-oriented programming languages include Base/Superclass, abstract class, and Derived/Child class takes place. Unit test frameworks, drivers, stubs and mocks /fake objects used in it. It works on the basis of a White box technique

Integration testing: It is a type of software testing in which the different units, modules or components of a software application are tested as a combined entity. However, these modules may be coded by different programmers.
The aim of integration testing is to test the interfaces between the modules and expose any defects that may arise when these components are integrated and need to interact with each other.

Regression testing: It is a process of testing the software and analyzing whether the change of code, update, or improvements of the application has not affected the software’s existing functionality.
Regression testing in software engineering ensures the overall stability and functionality of existing features of the software. Regression testing ensures that the overall system stays sustainable under continuous improvements whenever new features are added to the code to update the software. 
Regression testing helps target and reduce the risk of code dependencies, defects, and malfunction, so the previously developed and tested code stays operational after the modification.

In this project my part will be writing test cases based on the scope and functionality which cover the junit and intermediate test(UI).

### 1.2 Test Selection 

In this project we will work with white box testing which cover the testing each and every line. White-box Testing is a type of testing method where the tester (a developer testing the application) is aware of the system internals. Its implementation is transparent. The tester is required to know the internal workings of the code. The objective is to ensure that each and every line of the code is tested. The tester detects all logical, design and typographical errors. The tester also needs to validate the internal structure of the item under consideration along with the output.

### 1.3 Adequacy Criterion
One important aspect of software testing is to decide when enough testing has been done. There are various ways to classify test adequacy criteria Hence, an adequacy criterion can be classified as:
* Specification-based: The test cases should be developed in such a manner that they should cover all the features that have been identified from the requirements specification. 
* Program-based: The test cases are designed in terms of the program under test. A test set is said to be adequate if the program under test has been thoroughly exercised. 
* Design based: The test criterion determines the configurations that must be covered in an adequate design-level test. 
* Combined specification and program based criteria: This criterion uses the ideas of both program-based and specification based criteria.
* Structural testing: It specifies testing requirements in terms of the coverage of a particular set of elements in the structure of the program or the specification. 
* Fault-based testing: It focuses on detecting faults (i.e., defects) in the software. An adequacy criterion of this approach is some measurement of the fault detecting ability of test sets.
* Error-based testing: It requires test cases to check the program on certain errorprone points according to our knowledge about how programs typically depart from their specifications

### 1.4 Bug Tracking
A bug tracking system is software that keeps track of bugs that the user encountered in any software development or in any project. In market lot of tools are available like Jira,Zoho bugtracker ,Bug zilla. But as of now we are plan to maintain the issue in spreadsheet

### 1.5 Technology
 For testing in our application we are planning to use the junit ,mockito and espresso (yet to confirm with team) .
* JUnit is a “Unit Testing” framework for Java Applications which is already included by default in android studio. It is an automation framework for Unit as well as UI Testing. It contains annotations such as @Test, @Before, @After, etc. Here we will be using only @Test annotation to keep the article easy to understand.
* Mockito is used to mock interfaces so that a dummy functionality can be added to a mock interface that can be used in unit testing. This tutorial should help you learn how to create unit tests with Mockito as well as how to use its APIs in a simple and intuitive way.
* Espresso is reliable android UI test tool it  is targeted at developers, who believe that automated testing is an integral part of the development lifecycle. Espresso’s full power is unlocked by those who are familiar with the codebase under test.

## 2 Test Cases

|SN |	TEST CASES                                                                                                              	 | EXPECTED RESULT	                                                                                 | ACTUAL RESULT	                                                                              | STATUSPASS/FAIL    |
|---|--------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|--------------------|
|1  | To check the add item functionality  enter the item name in the list			                                           |It must add the item name in the list                                                                  |User can add the item name                                                                        | PASS               |
|2  | To ensure that the added list is saved               			                                                             |Added item must be saved in the list                                                                   |Added item got saved in the list                                                                  | PASS               |
|3  | Ensure while adding the item similar name cannot be added			                                                       |The list of item must not be duplicated                                                                |User are not allowed to add similar name items in the list.                                       | PASS               |
|4  | To check the delete item functionality when deleted item should remove immediately			                               |User must be able to check the delete item functionality when deleted item should remove immediately   |User are able to check the delete item functionality when deleted item are removed immediately    | PASS               |      
|5  | To check if the user can modify the quantity of the item added                                                            	 |User should be able to modify the quantity of item                                                     |User are able to modify the quantity of the item in the list                                      | PASS               |		
|6  | Ensure the user can able to mark the specific item			                                                             |User should be able to mark the specific item                                                          |User are able to mark the specific item                                                           | PASS               |
|7  | Ensure the use can able to  remove the marked item			                                                             |User should be able to remove the marked item                                                          |User are able to remove the marked item                                                           | PASS               |
|8  | Ensure the list when do the Check-off marks option all marks should be  removed from the list                                  |User should be able to do the Check-off marks option that should remove all marks from the list.       |User are aable to do check-off marks option that remove all marks from the list                   | PASS               |
|9  | Ensure user can be able to add the list of item from add new Grocery List                                                      |User should be able to add the list of item from add new Grocery List                                  |User are able to add the List of item from add new Grocery List                                   | PASS               |
|10 | Ensure user can be able to delete the list of item from delete Grocery List                                                    |User should be able to delete the list of item from delete Grocery List                                |User are able to delete the list of item from delete grocery list                                 | PASS               |
 			                                                                                                   
 			    			                                                                                                                                                       













 
