package demo.paykey.paykeyassignment;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import demo.paykey.paykeyassignment.CalculatorContract;
import demo.paykey.paykeyassignment.evaluator.EvaluationErrorException;
import demo.paykey.paykeyassignment.evaluator.Evaluator;
import demo.paykey.paykeyassignment.storage.HistoryStorage;
import demo.paykey.paykeyassignment.storage.StorageException;

/**
 * Created by alexeykrichun on 10/01/2017.
 */

public class CalculatorPresenter implements CalculatorContract.Presenter {
    private final Evaluator evaluator;
    private final CalculatorContract.View view;
    private final HistoryStorage<String> historyStorage;

    private final Executor executor = Executors.newSingleThreadExecutor();

    public CalculatorPresenter(Evaluator evaluator, CalculatorContract.View view, HistoryStorage<String> historyStorage) {
        this.evaluator = evaluator;
        this.view = view;
        this.historyStorage = historyStorage;
    }

    @Override
    public void evaluate(final String input) {
        try {
            view.showResult(String.valueOf(evaluator.evaluate(input)));
            addToHistory(input);
        } catch (EvaluationErrorException exception) {
            view.showError(input, exception.getErrorPositions(), exception.getMessage());
        }
    }

    private void addToHistory(final String input) {
        view.historyItemAdded(input);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    historyStorage.addHistoryItem(input);
                } catch (StorageException e) {
                    historyLoadError(e.getMessage());
                }
            }
        });
    }

    @Override
    public void loadHistory() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<String> result = historyStorage.getHistory();
                    historyLoaded(result);
                } catch (StorageException e) {
                    historyLoadError(e.getMessage());
                }
            }
        });
    }

    private void historyLoaded(List<String> history) {
        view.updateHistory(history);
    }

    private void historyLoadError(String message) {
        view.onHistoryLoadError(message);
    }
}
