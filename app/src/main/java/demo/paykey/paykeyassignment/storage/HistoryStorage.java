package demo.paykey.paykeyassignment.storage;

import java.util.List;

/**
 * Created by alexy on 07.01.2017.
 */

public interface HistoryStorage<T> {
    void addHistoryItem(T item);
    List<T> getHistory();
    void clear();
}
