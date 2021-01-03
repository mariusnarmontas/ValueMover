package info.narmontas.valuemover;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static info.narmontas.valuemover.MoveValues.*;
import static org.junit.jupiter.api.Assertions.*;

public class MoveValuesFromParentClassTest {
    private static ChildrenType inputObject;
    private ChildrenType outputObject;

    @BeforeAll
    public static void init() {
        inputObject = new ChildrenType();
        inputObject.setId(11L);
        inputObject.setName("Test Name");
        inputObject.setDescription("Test Description");
        inputObject.setLevel(1);
    }

    @BeforeEach
    public void createOutputObject() {
        outputObject = new ChildrenType();
    }

    @Test
    public void moveAll() {
        forType(ChildrenType.class)
                .takeValuesFrom(inputObject)
                .putValuesTo(outputObject)
                .moveAll();

        assertEquals(inputObject.getId(), outputObject.getId());
        assertEquals(inputObject.getName(), outputObject.getName());
        assertEquals(inputObject.getDescription(), outputObject.getDescription());
        assertEquals(inputObject.getLevel(), outputObject.getLevel());
    }

    @Test
    public void moveAll_flat() {
        forType(ChildrenType.class).flat()
                .takeValuesFrom(inputObject)
                .putValuesTo(outputObject)
                .moveAll();

        assertNotEquals(inputObject.getId(), outputObject.getId());
        assertNotEquals(inputObject.getName(), outputObject.getName());
        assertEquals(inputObject.getDescription(), outputObject.getDescription());
        assertEquals(inputObject.getLevel(), outputObject.getLevel());
    }
}
