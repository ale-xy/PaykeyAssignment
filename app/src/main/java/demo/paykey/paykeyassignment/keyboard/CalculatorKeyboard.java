package demo.paykey.paykeyassignment.keyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.ListView;

import demo.paykey.paykeyassignment.R;

/**
 * Created by alexy on 07.01.2017.
 */

public class CalculatorKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener{
    private final static int KEYCODE_CLEAR = -10;

    private KeyboardView keyboardView;
    private ListView listView;
    private Keyboard keyboard;

    @Override
    public View onCreateInputView() {
        ViewGroup view = (ViewGroup)getLayoutInflater().inflate(R.layout.keyboard_layout, null);
        keyboardView = (KeyboardView)view.findViewById(R.id.keyboard);
        listView = (ListView)view.findViewById(R.id.history_list);
        keyboard = new Keyboard(this, R.xml.keyboard);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(this);
        return view;
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

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

    private void toggleList() {
        if (listView.getVisibility() != View.VISIBLE) {
            listView.setVisibility(View.VISIBLE);
            //todo disable input
        } else {
            listView.setVisibility(View.GONE);
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

