package demo.paykey.paykeyassignment.keyboard;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

/**
 * Created by alexeykrichun on 10/01/2017.
 */

public class HistoryListAdapter extends ArrayAdapter<String> {
    public HistoryListAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return super.getItem(getCount() - position - 1);
    }
}
