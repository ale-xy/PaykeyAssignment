package demo.paykey.paykeyassignment.evaluator.impl;

/**
 * Created by alexy on 07.01.2017.
 */

public interface ArithmeticOperation {
    /**
     * @return precedence of the operation. Greater precedence means that operation is executed earlier (e.g multiplication is before addition).
     */
    int getPrecedence();
    int result(int a, int b);
}
