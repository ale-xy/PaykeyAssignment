package demo.paykey.paykeyassignment.evaluator;

import java.util.HashMap;

/**
 * Created by alexy on 07.01.2017.
 */

public class ArithmeticOperations {
    private HashMap<String, ArithmeticOperation> operationHashMap = new HashMap<>(4);

    ArithmeticOperations() {
        operationHashMap.put("+", new Add());
        operationHashMap.put("-", new Subtract());
        operationHashMap.put("*", new Multiply());
        operationHashMap.put("/", new Divide());
    }

    ArithmeticOperation get(String symbol) {
        return operationHashMap.get(symbol);
    }

}
