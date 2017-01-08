package demo.paykey.paykeyassignment.evaluator;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by alexy on 07.01.2017.
 */

public class EvaluationErrorException extends Exception {
    private final SortedSet<Integer> errorPositions;

    public EvaluationErrorException(SortedSet<Integer> errorPositions) {
        this.errorPositions = errorPositions;
    }

    public SortedSet<Integer> getErrorPositions() {
        return errorPositions;
    }
}
