package com.polteq;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    private static Class<?> employee;

    @BeforeAll
    public static void setup() {
        try {
            employee = Class.forName("com.polteq.Employee");
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void employeeClassShouldExists() {
        assertNotNull(employee);
    }

    @Test
    public void shouldHaveFieldFirstName() {
        try {
            Field firstName = employee.getDeclaredField("firstName");
            // should be a private field
            assertTrue(Modifier.isPrivate(firstName.getModifiers()), "firstName field should be private");
            // should be type String
            assertEquals("java.lang.String", firstName.getType().getName(), "firstName should be type String");
            // should have a getter
            assertTrue(hasGetter(firstName), "firstName should have a getter");
            // should have a setter
            assertTrue(hasSetter(firstName));
        } catch (NoSuchFieldException e) {
            fail("Employee class must contain field: firstName");
        }
    }

    @Test
    public void shouldHaveFieldLastName() {
        try {
            Field lastName = employee.getDeclaredField("lastName");
            // should be a private field
            assertTrue(Modifier.isPrivate(lastName.getModifiers()), "lastName field should be private");
            // should be type String
            assertEquals("java.lang.String", lastName.getType().getName(), "lastName should be type String");
            // should have a getter
            assertTrue(hasGetter(lastName), "lastName should have a getter");
            // should have a setter
            assertTrue(hasSetter(lastName));
        } catch (NoSuchFieldException e) {
            fail("Employee class must contain field: lastName");
        }
    }

    @Test
    public void constructorShouldSetFields() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            Constructor<?> constructor = employee.getConstructor(String.class, String.class);
            Object marco = constructor.newInstance("Marco", "Maes");
            Method getFirstName = marco.getClass().getMethod("getFirstName");
            Method getLastName = marco.getClass().getMethod("getLastName");
            assertEquals(getFirstName.invoke(marco), "Marco", "The constructor did not set the firstName");
            assertEquals(getLastName.invoke(marco), "Maes", "The constructor did not set the lastName");
        } catch (NoSuchMethodException e) {
            fail("No such method: " + e.getMessage());
        }
    }

    @Test
    public void getFullNameShouldReturnFullName() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            Constructor<?> constructor = employee.getConstructor(String.class, String.class);
            Object marco = constructor.newInstance("Marco", "Maes");
            Method getFullName = marco.getClass().getMethod("getFullName");
            assertEquals(getFullName.invoke(marco), "Marco Maes", "Employee.getFullName() must return firstName + \" \" + lastName");
        } catch (NoSuchMethodException e) {
            fail("No such method: " + e.getMessage());
        }

    }

    private boolean hasGetter(Field field) {
        return hasGetterOrSetter(field, "get");
    }

    private boolean hasSetter(Field field) {
        return hasGetterOrSetter(field, "set");
    }

    private boolean hasGetterOrSetter(Field field, String prefix) {
        Optional<Method> getterOrSetter = Arrays.stream(employee.getMethods())
                .filter(f -> f.getName().equals(prefix + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1)))
                .findFirst();
        return getterOrSetter.isPresent();
    }

}
