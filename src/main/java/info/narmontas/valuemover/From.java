package info.narmontas.valuemover;

public class From<T> {

    private final MoveValues<T> moveValues;
    private T outputObject;

    From(MoveValues<T> moveValues) {
        this.moveValues = moveValues;
    }

    public To<T> putValuesTo(T outputObject) {
        this.outputObject = outputObject;
        return new To<T>(this);
    }

    MoveValues<T> getMoveValues() {
        return moveValues;
    }

    T getOutputObject() {
        return outputObject;
    }
}
