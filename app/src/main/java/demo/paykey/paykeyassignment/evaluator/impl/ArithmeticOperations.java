package demo.paykey.paykeyassignment.evaluator.impl;

/**
 * Created by alexy on 08.01.2017.
 */
public interface ArithmeticOperations {
    ArithmeticOperation get(String symbol);

    ArithmeticOperation get(char character);
}
