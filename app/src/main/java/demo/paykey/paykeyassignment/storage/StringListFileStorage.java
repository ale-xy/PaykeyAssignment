package demo.paykey.paykeyassignment.storage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexeykrichun on 10/01/2017.
 */

public class StringListFileStorage implements HistoryStorage<String> {
    private static final String STORAGE_FILE = "CalculatorKeyboardHistory";

    private final Context context;

    public StringListFileStorage(Context context) {
        this.context = context;
    }


    @Override
    public void addHistoryItem(String item) throws StorageException{
        File file = getStorageFile();
        FileOutputStream fileOutputStream;

        try {
            if (file.exists()) {
                fileOutputStream = context.openFileOutput(STORAGE_FILE, Context.MODE_APPEND);
            } else {
                file.createNewFile();
                fileOutputStream = context.openFileOutput(STORAGE_FILE, Context.MODE_PRIVATE);
            }

            fileOutputStream.write(item.getBytes());
            fileOutputStream.write('\n');
            fileOutputStream.close();
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }

    @Override
    public List<String> getHistory() throws StorageException {
        File file = getStorageFile();

        if (!file.exists()) {
            return null;
        }

        List<String> strings = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line;
            do {
                line = reader.readLine();
                if (!TextUtils.isEmpty(line)) {
                    strings.add(line);
                }
            } while (line != null);
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

        return strings;
    }

    @NonNull
    private File getStorageFile() {
        return new File(context.getFilesDir(), STORAGE_FILE);
    }
}
