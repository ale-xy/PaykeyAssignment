package demo.paykey.paykeyassignment.evaluator.impl;

import java.util.HashMap;

/**
 * Created by alexy on 08.01.2017.
 */

public class ArithmeticOperationsJava implements ArithmeticOperations {

    private final HashMap<String, ArithmeticOperation> operationHashMap = new HashMap<>(4);

    ArithmeticOperationsJava() {
        operationHashMap.put("+", new ArithmeticOperation() {
            @Override
            public int getPrecedence() {
                return 0;
            }

            @Override
            public int result(int a, int b) {
                return a+b;
            }
        });
        operationHashMap.put("-", new ArithmeticOperation() {
            @Override
            public int getPrecedence() {
                return 0;
            }

            @Override
            public int result(int a, int b) {
                return a-b;
            }
        });
        operationHashMap.put("*", new ArithmeticOperation() {
            @Override
            public int getPrecedence() {
                return 1;
            }

            @Override
            public int result(int a, int b) {
                return a*b;
            }
        });
        operationHashMap.put("/", new ArithmeticOperation() {
            @Override
            public int getPrecedence() {
                return 1;
            }

            @Override
            public int result(int a, int b) {
                return a/b;
            }
        });
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
