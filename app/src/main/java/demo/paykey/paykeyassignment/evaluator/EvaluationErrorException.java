package demo.paykey.paykeyassignment.evaluator;

import java.util.SortedSet;

/**
 * Created by alexy on 07.01.2017.
 */

public class EvaluationErrorException extends Exception {
    private final SortedSet<Integer> errorPositions;

    public EvaluationErrorException(String message) {
        super(message);
        errorPositions = null;
    }

    public EvaluationErrorException(String message, SortedSet<Integer> errorPositions) {
        super(message);
        this.errorPositions = errorPositions;
    }

    public SortedSet<Integer> getErrorPositions() {
        return errorPositions;
    }
}
