package demo.paykey.paykeyassignment.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.paykey.paykeyassignment.CalculatorContract;
import demo.paykey.paykeyassignment.CalculatorPresenter;
import demo.paykey.paykeyassignment.evaluator.Evaluator;
import demo.paykey.paykeyassignment.evaluator.impl.ArithmeticEvaluator;
import demo.paykey.paykeyassignment.evaluator.impl.ArithmeticOperations;
import demo.paykey.paykeyassignment.evaluator.impl.ArithmeticOperationsNative;
import demo.paykey.paykeyassignment.storage.HistoryStorage;
import demo.paykey.paykeyassignment.storage.StringListFileStorage;

/**
 * Created by alexy on 09.01.2017.
 */

@Module
public class CalculatorModule {
    private final Context context;
    private final CalculatorContract.View view;

    public CalculatorModule(Context context, CalculatorContract.View view) {
        this.context = context;
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
    public HistoryStorage<String> providesHistoryStorage() {
        return new StringListFileStorage(context);
    }

    @Provides
    @Singleton
    public CalculatorContract.Presenter providesPresenter(Evaluator evaluator, HistoryStorage<String> historyStorage) {
        return new CalculatorPresenter(evaluator, view, historyStorage);
    }
}
