package demo.paykey.paykeyassignment.keyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.SortedSet;

import demo.paykey.paykeyassignment.CalculatorContract;
import demo.paykey.paykeyassignment.CalculatorPresenter;
import demo.paykey.paykeyassignment.R;
import demo.paykey.paykeyassignment.storage.StringListFileStorage;

/**
 * Created by alexy on 07.01.2017.
 */

public class CalculatorKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener, CalculatorContract.View{
    private KeyboardView keyboardView;
    private ListView listView;
    private Keyboard keyboard;

    private HistoryListAdapter historyListAdapter;

    CalculatorContract.Presenter presenter = new CalculatorPresenter(this, new StringListFileStorage(this));

    @Override
    public View onCreateInputView() {
        ViewGroup view = (ViewGroup)getLayoutInflater().inflate(R.layout.keyboard_layout, null);
        keyboardView = (KeyboardView)view.findViewById(R.id.keyboard);
        listView = (ListView)view.findViewById(R.id.history_list);

        historyListAdapter = new HistoryListAdapter(this, R.layout.list_item_layout, R.id.history_list_item);
        listView.setAdapter(historyListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setText(historyListAdapter.getItem(position));
                hideList();

            }
        });

        presenter.loadHistory();

        keyboard = new Keyboard(this, R.xml.keyboard);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(this);
        return view;
    }

    private void hideList() {
        listView.setVisibility(View.GONE);
    }

    @Override
    public void onKey(int primaryCode, int[] ints) {
        InputConnection inputConnection = getCurrentInputConnection();
        switch (primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                inputConnection.deleteSurroundingText(1,0);
                break;
            case Keyboard.KEYCODE_MODE_CHANGE:
                toggleList();
                //todo list
                break;
            case Keyboard.KEYCODE_DONE:
                presenter.evaluate(getInputText());
                //todo calc
                break;
            case Keyboard.KEYCODE_CANCEL:
                clearAllText();
                break;
            default:
                String character = String.valueOf((char)primaryCode);
                inputConnection.commitText(character, 1);
        }

    }

    private String getInputText() {
        InputConnection inputConnection = getCurrentInputConnection();
        return inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text.toString();
    }

    private void toggleList() {
        if (listView.getVisibility() != View.VISIBLE) {
            listView.setVisibility(View.VISIBLE);
            //todo disable input
        } else {
            hideList();
            //todo enable input
        }
    }

    private void clearAllText() {
        InputConnection inputConnection = getCurrentInputConnection();
        CharSequence currentText = inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text;
        CharSequence beforeCursor = inputConnection.getTextBeforeCursor(currentText.length(), 0);
        CharSequence afterCursor = inputConnection.getTextAfterCursor(currentText.length(), 0);
        inputConnection.deleteSurroundingText(beforeCursor.length(), afterCursor.length());
    }

    private void setText(String text) {
        clearAllText();
        InputConnection inputConnection = getCurrentInputConnection();
        inputConnection.commitText(text, 0);
    }

    @Override
    public void showResult(String result) {

    }

    @Override
    public void showError(SortedSet<Integer> errorPositions) {

    }

    @Override
    public void onHistoryLoaded(List<String> history) {
        historyListAdapter.clear();
        historyListAdapter.addAll(history);
        historyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void historyItemAdded(String item) {
        historyListAdapter.add(item);
        historyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHistoryLoadError(String error) {

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

