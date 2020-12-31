package info.narmontas.valuemover;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static info.narmontas.valuemover.MoveValues.*;

class MoveValuesTest {

    private static TestType inputObject;
    private TestType outputObject;

    @BeforeAll
    public static void init() {
        inputObject = new TestType();
        inputObject.setId(11L);
        inputObject.setName("Test Object");
        inputObject.setLevel(1);
        inputObject.setChildrenNames(new ArrayList<>());
        inputObject.getChildrenNames().add("Children 1");
        inputObject.getChildrenNames().add("Children 2");
    }

    @BeforeEach
    public void createOutputObject() {
        outputObject = new TestType();
    }

    @Test
    public void moveAll() {
        System.out.println(outputObject);

        forType(TestType.class)
                .takeValuesFrom(inputObject)
                .pubValuesTo(outputObject)
                .moveAll();

        System.out.println(outputObject);

        assertEquals(inputObject.getId(), outputObject.getId());
        assertEquals(inputObject.getName(), outputObject.getName());
        assertEquals(inputObject.getLevel(), outputObject.getLevel());
        assertEquals(inputObject.getChildrenNames(), outputObject.getChildrenNames());
    }

    @Test
    public void moveExcept() {
        System.out.println(outputObject);

        forType(TestType.class)
                .takeValuesFrom(inputObject)
                .pubValuesTo(outputObject)
                .moveExcept("childrenNames");

        System.out.println(outputObject);

        assertEquals(inputObject.getId(), outputObject.getId());
        assertEquals(inputObject.getName(), outputObject.getName());
        assertEquals(inputObject.getLevel(), outputObject.getLevel());
        assertNull(outputObject.getChildrenNames());
    }
}