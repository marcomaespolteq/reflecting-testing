# Creating Classes

In this exercise you will learn how to write tests for a class that doesn't yet exist.
This scenario comes up when you want to give someone an exercise that involves
writing classes, fields, methods, etc...

## Step One: Create Employee Class

Create an Employee class in the `com.abc` package.

### Step Two: Create a First Name field

- firstName
    - access modifier: private
    - type: String
    - getter: getFirstName()
    - setter: setFirstName(String firstName)

### Step Three: Create a Last Name field

- lastName
    - access modifier: private
    - type: String
    - getter: getLastName()
    - setter: getLastName(String lastName)

### Step Four: Create a constructor

The constructor should take in firstName and lastName as arguments and set each field.

### Step Five: Create a new method

- getFullName()
    - returns a String
    - should return firstName + " " + lastName
    