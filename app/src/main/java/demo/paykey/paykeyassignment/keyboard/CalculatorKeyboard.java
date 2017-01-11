package demo.paykey.paykeyassignment.keyboard;

import android.graphics.Color;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.ListView;
import android.widget.TextView;


import demo.paykey.paykeyassignment.CalculatorContract;

import java.util.List;
import java.util.SortedSet;

import demo.paykey.paykeyassignment.R;
import demo.paykey.paykeyassignment.di.CalculatorComponent;
import demo.paykey.paykeyassignment.di.CalculatorModule;
import demo.paykey.paykeyassignment.di.DaggerCalculatorComponent;

/**
 * Created by alexy on 07.01.2017.
 */

public class CalculatorKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener, CalculatorContract.View{
    private KeyboardView keyboardView;
    private View listViewFrame;
    private ListView listView;
    private Keyboard keyboard;
    private TextView errorView;

    private CalculatorContract.Presenter presenter;

    private boolean inputEnabled = true;

    private HistoryListAdapter historyListAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        CalculatorComponent calculatorComponent =
                DaggerCalculatorComponent.builder().calculatorModule(new CalculatorModule(this, this)).build();
        presenter = calculatorComponent.getPresenter();
    }

    @Override
    public View onCreateInputView() {
        ViewGroup view = (ViewGroup)getLayoutInflater().inflate(R.layout.keyboard_layout, null);
        keyboardView = (KeyboardView)view.findViewById(R.id.keyboard);

        setupListView(view);
        hideList();

        presenter.loadHistory();

        errorView = (TextView)view.findViewById(R.id.error_message);
        errorView.setVisibility(View.GONE);

        keyboard = new Keyboard(this, R.xml.keyboard);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(this);
        return view;
    }

    private void setupListView(ViewGroup view) {
        listViewFrame = view.findViewById(R.id.history_list_frame);
        listView = (ListView)view.findViewById(R.id.history_list);

        historyListAdapter = new HistoryListAdapter(this, R.layout.list_item_layout, R.id.history_list_item);
        historyListAdapter.setListener(new HistoryListAdapter.Listener() {
            @Override
            public void onItemClicked(String value) {
                setText(value);
                hideList();
            }
        });

        listView.setAdapter(historyListAdapter);

        listView.setItemsCanFocus(false);
        View emptyView = view.findViewById(R.id.no_items_text);
        listView.setEmptyView(emptyView);
    }

    @Override
    public void onKey(int primaryCode, int[] ints) {
        if (errorView.getVisibility() != View.GONE) {
            errorView.setVisibility(View.GONE);
        }

        if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {
            toggleList();
            return;
        }

        if (!inputEnabled) {
            return;
        }

        InputConnection inputConnection = getCurrentInputConnection();
        switch (primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                inputConnection.deleteSurroundingText(1,0);
                break;
            case Keyboard.KEYCODE_DONE:
                presenter.evaluate(getCurrentText(inputConnection));
                break;
            case Keyboard.KEYCODE_CANCEL:
                clearText(inputConnection);
                break;
            default:
                String character = String.valueOf((char)primaryCode);
                inputConnection.commitText(character, 1);
        }
    }

    private String getCurrentText(InputConnection inputConnection) {
        return inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text.toString();
    }

    private void toggleList() {
        if (listViewFrame.getVisibility() != View.VISIBLE) {
            showList();
        } else {
            hideList();
        }
    }

    private void hideList() {
        listViewFrame.setVisibility(View.GONE);
        inputEnabled = true;
    }

    private void showList() {
        listViewFrame.setVisibility(View.VISIBLE);
        inputEnabled = false;
    }

    private void clearText(InputConnection inputConnection) {
        CharSequence currentText = inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text;
        CharSequence beforeCursor = inputConnection.getTextBeforeCursor(currentText.length(), 0);
        CharSequence afterCursor = inputConnection.getTextAfterCursor(currentText.length(), 0);
        inputConnection.deleteSurroundingText(beforeCursor.length(), afterCursor.length());
    }

    private void setText(String text) {
        InputConnection inputConnection = getCurrentInputConnection();
        clearText(inputConnection);
        inputConnection.commitText(text, 0);
    }

    @Override
    public void onHistoryLoadError(String error) {
        showError(null, null, error);
    }

    @Override
    public void updateHistory(List<String> history) {
        historyListAdapter.clear();

        if (history != null && !history.isEmpty()) {
            historyListAdapter.addAll(history);
        }

        historyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void historyItemAdded(String item) {
        historyListAdapter.add(item);
        historyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showResult(String result) {
        InputConnection inputConnection = getCurrentInputConnection();
        clearText(inputConnection);
        inputConnection.commitText(result, 1);
    }

    @Override
    public void showError(String input, SortedSet<Integer> errorPositions, String message) {
        SpannableStringBuilder builder = new SpannableStringBuilder(message);

        if (!TextUtils.isEmpty(input) && errorPositions != null && !errorPositions.isEmpty()) {
            builder.append("\n");

            SpannableString spannableString = new SpannableString(input);
            for (int index : errorPositions) {
                ForegroundColorSpan span = new ForegroundColorSpan(Color.YELLOW);
                spannableString.setSpan(span, index, index + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }

            builder.append(spannableString);
        }

        errorView.setText(builder);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }
    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}

