package demo.paykey.paykeyassignment.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.paykey.paykeyassignment.CalculatorContract;
import demo.paykey.paykeyassignment.CalculatorPresenter;
import demo.paykey.paykeyassignment.evaluator.Evaluator;
import demo.paykey.paykeyassignment.evaluator.impl.ArithmeticEvaluator;
import demo.paykey.paykeyassignment.evaluator.impl.ArithmeticOperation;
import demo.paykey.paykeyassignment.evaluator.impl.ArithmeticOperations;
import demo.paykey.paykeyassignment.evaluator.impl.ArithmeticOperationsNative;

/**
 * Created by alexy on 09.01.2017.
 */

@Module
public class CalculatorModule {
    private final CalculatorContract.View view;

    public CalculatorModule(CalculatorContract.View view) {
        this.view = view;
    }


    @Provides
    @Singleton
    public ArithmeticOperations providesArithmeticOperations() {
        return new ArithmeticOperationsNative();
    }

    @Provides
    @Singleton
    public Evaluator providesEvaluator(ArithmeticOperations arithmeticOperations) {
        return new ArithmeticEvaluator(arithmeticOperations);
    }

    @Provides
    @Singleton
    public CalculatorContract.Presenter providesPresenter(Evaluator evaluator) {
        return new CalculatorPresenter(evaluator, view);
    }
}
