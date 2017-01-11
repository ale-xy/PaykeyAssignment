package demo.paykey.paykeyassignment.keyboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import demo.paykey.paykeyassignment.R;

/**
 * Created by alexeykrichun on 10/01/2017.
 */

class HistoryListAdapter extends ArrayAdapter<String> {
    private Listener listener;

    public interface Listener {
        void onItemClicked(String value);
    }

    public HistoryListAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return super.getItem(getCount() - position - 1);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_layout, parent, false);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        TextView text = (TextView)v.findViewById(R.id.history_list_item);
                        String item = text.getText().toString();
                        listener.onItemClicked(item);
                    }
                }
            });
        }

        TextView text = (TextView)convertView.findViewById(R.id.history_list_item);
        text.setText(getItem(position));
        return convertView;
    }
}
