package demo.paykey.paykeyassignment.evaluator.impl;

import android.text.TextUtils;

import java.util.EmptyStackException;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import demo.paykey.paykeyassignment.evaluator.EvaluationErrorException;
import demo.paykey.paykeyassignment.evaluator.Evaluator;

/**
 * Created by alexeykrichun on 08/01/2017.
 */

public class ArithmeticEvaluator implements Evaluator {
    private final ArithmeticOperations arithmeticOperations;

    public ArithmeticEvaluator(ArithmeticOperations arithmeticOperations) {
        this.arithmeticOperations = arithmeticOperations;
    }

    private void preValidateInput(String input) throws EvaluationErrorException {
        if(TextUtils.isEmpty(input)) {
            throw new EvaluationErrorException("Nothing to calculate!");
        }

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
            throw new EvaluationErrorException("Evaluation error!", invalidCharacters);
        }
    }

    private boolean isOperation(char character) {
        return arithmeticOperations.get(String.valueOf(character)) != null;
    }


    @Override
    public int evaluate(String input) throws EvaluationErrorException {
        preValidateInput(input);

        Stack<Integer> arguments = new Stack<>();
        Stack<ArithmeticOperation> operations = new Stack<>();

        int index = 0;
        while (index < input.length()) {
            char character = input.charAt(index);

            if (Character.isWhitespace(character)) {
                index++;
                continue;
            }

            if (Character.isDigit(character)) {
                index = pushNumber(arguments, input, index);
            } else if (isOperation(character)) {
                pushOperation(operations, arguments, arithmeticOperations.get(character));
                index++;
            }
        }

        while (!operations.isEmpty()) {
            pushCurrentResult(arguments, operations);
        }

        return arguments.pop();
    }

    private void pushCurrentResult(Stack<Integer> arguments, Stack<ArithmeticOperation> operations) throws EvaluationErrorException {
        //todo int overflow
        try {
            int arg2 = arguments.pop();
            int arg1 = arguments.pop();

            arguments.push(operations.pop().result(arg1, arg2));
        } catch (ArithmeticException e) {
            throw new EvaluationErrorException("Division by zero!");
        } catch (EmptyStackException e) {
            throw new EvaluationErrorException("Missing argument!");
        }
    }

    private int pushNumber(Stack<Integer> stack, String input, int index) {
        int value = 0;

        while (index < input.length()) {
            char character = input.charAt(index);
            if (Character.isDigit(character)) {
                int digit = Character.digit(character, 10);
                value = value * 10 + digit;
                index++;
            } else {
                break;
            }
        }

        stack.push(value);
        return index;
    }

    private void pushOperation(Stack<ArithmeticOperation> operations, Stack<Integer> arguments, ArithmeticOperation operation) throws EvaluationErrorException {
        while (!operations.isEmpty() && operation.getPrecedence() < operations.peek().getPrecedence()) {
            pushCurrentResult(arguments, operations);
        }
        operations.push(operation);
    }
}
