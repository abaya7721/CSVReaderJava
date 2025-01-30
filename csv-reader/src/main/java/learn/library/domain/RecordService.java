package learn.library.domain;

import learn.library.data.FileOpenReader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<LocalDate> getDateRecords() {
        List<String> records = getAllRecords();
        List<LocalDate> dates = new ArrayList<>();
        records.removeFirst();
        for (String record : records) {
            String[] line = record.split(",");
            String dateField = line[0];
            List<Integer> splitDate = new ArrayList<>();
            for (String item : Arrays.stream(dateField.split("-")).toList()) {
                splitDate.add(Integer.parseInt(item));
            }
            LocalDate date = LocalDate.of(splitDate.get(0), splitDate.get(1), splitDate.get(2));
            dates.add(date);
        }
        return dates;
    }
    


}

