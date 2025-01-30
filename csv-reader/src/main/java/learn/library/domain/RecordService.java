package learn.library.domain;

import learn.library.data.FileOpenReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RecordService {
    private final FileOpenReader reader;

    public RecordService(String path) {
        reader = new FileOpenReader(path);
    }

    public List<String> getAllRecords() {
        return reader.readFile();
    }

    public List<String> getRecordsByCategory() {
        List<String> records = getAllRecords();
        List<String> categories = new ArrayList<>();
        records.removeFirst();
        for (String record : records) {
            String[] line = record.split(",");
            if (!categories.contains(line[1])) {
                categories.add(line[1]);
            }
        }
        return categories;
    }
}

