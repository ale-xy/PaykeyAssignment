package demo.paykey.paykeyassignment.evaluator;

/**
 * Created by alexy on 07.01.2017.
 */

public class Subtract implements ArithmeticOperation {
    @Override
    public native int result(int a, int b);
}