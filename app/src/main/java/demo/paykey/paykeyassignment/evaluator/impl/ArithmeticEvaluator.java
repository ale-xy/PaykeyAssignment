package demo.paykey.paykeyassignment.evaluator.impl;

import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

import demo.paykey.paykeyassignment.evaluator.EvaluationErrorException;
import demo.paykey.paykeyassignment.evaluator.Evaluator;

/**
 * Created by alexeykrichun on 08/01/2017.
 */

public class ArithmeticEvaluator implements Evaluator {


    private final ArithmeticOperations arithmeticOperations = new ArithmeticOperations();



    private void preValidateInput(String input) throws EvaluationErrorException {
        SortedSet<Integer> invalidCharacters = new TreeSet<>();
        boolean previousOperation = false;

        for (int i=0; i < input.length(); i++) {
            char character = input.charAt(i);

            if (Character.isWhitespace(character)) {
                continue;
            }

            if (!Character.isDigit(character) && !isOperation(character)) {
                invalidCharacters.add(i);
            }

            if (previousOperation && isOperation(character)) {
                invalidCharacters.add(i);
            }

            previousOperation = isOperation(character);
        }

        if (!invalidCharacters.isEmpty()) {
            throw new EvaluationErrorException(invalidCharacters);
        }
    }

    private boolean isOperation(char character) {
        return arithmeticOperations.get(String.valueOf(character)) != null;
    }



    @Override
    public int evaluate(String input) throws EvaluationErrorException {
        preValidateInput(input);

        Queue<Integer> arguments = new LinkedList<>();
        Queue<ArithmeticOperation> operations = new LinkedList<>();

        int index = 0;
        while (index < input.length()) {
            char character = input.charAt(index++);

            if (Character.isWhitespace(character)) {
                continue;
            }

            if (Character.isDigit(character)) {
                index = pushNumber(arguments, input, index);
            } else if (isOperation(character)) {
                pushOperation(operations, arguments, arithmeticOperations.get(character));
            }

        }

        return 0;
    }

    private int pushNumber(Queue<Integer> queue, String input, int index) {
        int value = 0;

        while (index < input.length()) {
            char character = input.charAt(index++);
            if (Character.isDigit(character)) {
                int digit = Character.digit(character, 10);
                value = value * 10 + digit;
            } else {
                break;
            }
        }

        queue.add(value);
        return index;
    }

    private void pushOperation(Queue<ArithmeticOperation> operations, Queue<Integer> arguments, ArithmeticOperation operation) {

    }
}
