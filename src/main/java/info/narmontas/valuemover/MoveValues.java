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

    public static <T> MoveValues<T> forFlatType(Class<T> clazz) {
        MoveValues<T> mv = new MoveValues<T>(clazz);
        mv.setMoveParentValues(false);
        return mv;
    }

    public From<T> takeValuesFrom(T inputObject) {
        this.inputObject = inputObject;
        return new From<T>(this);
    }

    Class<T> getType() {
        return type;
    }

    void setMoveParentValues(Boolean moveParentValues) {
        this.moveParentValues = moveParentValues;
    }

    T getInputObject() {
        return inputObject;
    }

    Boolean getMoveParentValues() {
        return moveParentValues;
    }
}
