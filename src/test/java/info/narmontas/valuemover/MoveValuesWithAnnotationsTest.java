package info.narmontas.valuemover;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static info.narmontas.valuemover.MoveValues.*;
import static org.junit.jupiter.api.Assertions.*;

public class MoveValuesWithAnnotationsTest {

    private static AnnotatedTestType inputObject;
    private AnnotatedTestType outputObject;

    @BeforeAll
    public static void init() {
        inputObject = new AnnotatedTestType();
        inputObject.setId(11L);
        inputObject.setLevel(1);
        inputObject.setDescription("Test Description");
        inputObject.setIgnoredField("Ignored Field");
    }

    @BeforeEach
    public void createOutputObject() {
        outputObject = new AnnotatedTestType();
    }

    @Test
    public void moveAll() {
        forType(AnnotatedTestType.class)
                .takeValuesFrom(inputObject)
                .putValuesTo(outputObject)
                .moveAll();

        assertEquals(inputObject.getId(), outputObject.getId());
        assertEquals(inputObject.getLevel(), outputObject.getLevel());
        assertEquals(inputObject.getDescription(), outputObject.getDescription());
        assertNull(outputObject.getIgnoredField());
    }

    @Test
    public void moveExcept() {
        forType(AnnotatedTestType.class)
                .takeValuesFrom(inputObject)
                .putValuesTo(outputObject)
                .moveExcept("description");

        assertEquals(inputObject.getId(), outputObject.getId());
        assertEquals(inputObject.getLevel(), outputObject.getLevel());
        assertNull(outputObject.getDescription());
        assertNull(outputObject.getIgnoredField());
    }
}
