package demo.paykey.paykeyassignment.evaluator.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by alexy on 08.01.2017.
 */
@RunWith(JUnit4.class)
public class ArithmeticEvaluatorTest {
    private final ArithmeticEvaluator arithmeticEvaluator = new ArithmeticEvaluator(new ArithmeticOperationsJava());

    @Test
    public void testEvaluate() throws Exception {
        assertEquals(4, arithmeticEvaluator.evaluate("2+2"));
        assertEquals(7, arithmeticEvaluator.evaluate("1+2*3"));
        assertEquals(6, arithmeticEvaluator.evaluate("2*2+2"));
        assertEquals(10, arithmeticEvaluator.evaluate("3335-3325"));
        assertEquals(6, arithmeticEvaluator.evaluate("4/2+8/2"));
        assertEquals(24, arithmeticEvaluator.evaluate("2048/256+65536/4096"));
    }

}