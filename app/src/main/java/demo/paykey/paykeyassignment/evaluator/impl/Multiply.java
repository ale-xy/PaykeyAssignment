package demo.paykey.paykeyassignment.evaluator.impl;

/**
 * Created by alexy on 07.01.2017.
 */

public class Multiply implements ArithmeticOperation {
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    public native int result(int a, int b);

    @Override
    public int getPrecedence() {
        return 2;
    }
}
