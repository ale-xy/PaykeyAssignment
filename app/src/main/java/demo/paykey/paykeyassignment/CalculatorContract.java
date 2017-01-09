package demo.paykey.paykeyassignment;

import java.util.List;
import java.util.SortedSet;

/**
 * Created by alexy on 07.01.2017.
 */

public interface CalculatorContract {
    interface View {
        void showResult(String result);
        void showError(String input, SortedSet<Integer> errorPositions, String message);
        void updateHistory(List<String> history);
        void historyItemAdded(String item);
        void onHistoryLoadError(String error);
    }

    interface Presenter {
        void evaluate(String input);
        void loadHistory();
    }
}
