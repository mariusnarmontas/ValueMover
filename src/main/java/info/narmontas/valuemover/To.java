package info.narmontas.valuemover;

import info.narmontas.valuemover.annotation.IgnoreValue;

import java.lang.reflect.Field;
import java.util.*;

public class To<T> {

    private final Set<String> exceptions = new HashSet<>();
    private final Class<T> type;
    private final T inputObject;
    private final T outputObject;
    private final Boolean getParentValues;

    To(From<T> from) {
        this.type = from.getMoveValues().getType();
        this.inputObject = from.getMoveValues().getInputObject();
        this.outputObject = from.getOutputObject();
        this.getParentValues = from.getMoveValues().getMoveParentValues();
    }

    /**
     * Move all values
     */
    public void moveAll() {
        process();
    }

    /**
     * Move all values, except exceptionFieldNames
     * @param exceptionsFieldNames String...
     */
    public void moveExcept(String... exceptionsFieldNames) {
        exceptions.addAll(Arrays.asList(exceptionsFieldNames));
        process();
    }


    // processor:
    private void process() {
        List<Field> declaredFields = getAllDeclaredFields();
        for (Field field : declaredFields) {
            checkIfAnnotated(field);
            assignAll(field);
        }
    }

    private List<Field> getAllDeclaredFields() {
        List<Field> fields = new ArrayList<>();
        assignFieldsRecursively(type, fields);
        return fields;
    }

    private void assignFieldsRecursively(Class<?> type, List<Field> fields) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null && !type.getSuperclass().getName().equals("Object")
                && getParentValues) {
            assignFieldsRecursively(type.getSuperclass(), fields);
        }
    }

    private void checkIfAnnotated(Field field) {
        if (field.isAnnotationPresent(IgnoreValue.class)) {
            exceptions.add(field.getName());
        }
    }

    private void assignAll(Field field) {
        if (!exceptions.contains(field.getName())) {
            field.setAccessible(true);
            assign(field);
        }
    }

    private void assign(Field field) {
        Object value = getValue(field);
        setValue(field, value);
    }

    private Object getValue(Field field) {
        Object value = null;
        try {
            value = field.get(inputObject);
        } catch (IllegalAccessException e) {
            System.out.println("Error: cannot get value of "
                    + field.getName());
            e.printStackTrace();
        }
        return value;
    }

    private void setValue(Field field, Object value) {
        try {
            field.set(outputObject, value);
        } catch (IllegalAccessException e) {
            System.out.println("Error: cannot assign value to "
                    + field.getName());
            e.printStackTrace();
        }
    }
}
