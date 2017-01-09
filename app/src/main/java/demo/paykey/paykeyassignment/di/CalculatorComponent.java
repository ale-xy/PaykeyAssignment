package demo.paykey.paykeyassignment.di;

import javax.inject.Singleton;

import dagger.Component;
import demo.paykey.paykeyassignment.CalculatorContract;
import demo.paykey.paykeyassignment.evaluator.Evaluator;

/**
 * Created by alexy on 09.01.2017.
 */

@Singleton
@Component(modules = {CalculatorModule.class})
public interface CalculatorComponent {
    void inject(CalculatorContract.View view);

    Evaluator getEvaluator();
    CalculatorContract.Presenter getPresenter();
}
