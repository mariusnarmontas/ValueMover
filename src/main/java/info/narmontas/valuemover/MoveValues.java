package info.narmontas.valuemover;

public class MoveValues<T> {

    private final Class<T> type;
    private T inputObject;

    private MoveValues(Class<T> type) {
        this.type = type;
    }

    public static <T> MoveValues<T> forType(Class<T> clazz) {
        return new MoveValues<T>(clazz);
    }

    public From<T> takeValuesFrom(T inputObject) {
        this.inputObject = inputObject;
        return new From<T>(this);
    }

    Class<T> getType() {
        return type;
    }

    T getInputObject() {
        return inputObject;
    }
}
