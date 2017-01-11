package demo.paykey.paykeyassignment.evaluator.impl;

import java.util.HashMap;

/**
 * Created by alexy on 07.01.2017.
 */

public class ArithmeticOperationsNative implements ArithmeticOperations {
    static {
        System.loadLibrary("native-lib");
    }

    private final HashMap<String, ArithmeticOperation> operationHashMap = new HashMap<>(4);

    public ArithmeticOperationsNative() {
        operationHashMap.put("+", new Add());
        operationHashMap.put("-", new Subtract());
        operationHashMap.put("*", new Multiply());
        operationHashMap.put("/", new Divide());
    }

    @Override
    public ArithmeticOperation get(String symbol) {
        return operationHashMap.get(symbol);
    }

    @Override
    public ArithmeticOperation get(char character) {
        return operationHashMap.get(String.valueOf(character));
    }

}
