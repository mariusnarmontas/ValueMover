package info.narmontas.valuemover;

public class MoveValues<T> {

    private final Class<T> type;
    private T inputObject;
    private Boolean moveParentValues = true;

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

    public MoveValues<T> flat() {
        moveParentValues = false;
        return this;
    }

    Class<T> getType() {
        return type;
    }

    T getInputObject() {
        return inputObject;
    }

    public Boolean getMoveParentValues() {
        return moveParentValues;
    }
}
