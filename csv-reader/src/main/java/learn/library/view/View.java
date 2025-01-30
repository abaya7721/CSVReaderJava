package learn.library.view;

import learn.library.domain.RecordService;

import java.io.FileNotFoundException;
import java.util.List;

public class View {
    private final RecordService recordService;

    public View(String path) throws FileNotFoundException {
        this.recordService = new RecordService(path);
    }

    public void showAllRecords() {
        List<String> records = recordService.getAllRecords();

        for (String record : records) {
            System.out.println(record);
        }
    }

    public void showCategoriesRecords() {
        List<String> categories = recordService.getRecordsByCategory();
        String header = recordService.getAllRecords().getFirst();
        List<String> records = recordService.getAllRecords();

        for (String category : categories) {
            System.out.println("Grouped by: " + category);
            System.out.println(header);
            for (String record : records) {
                if (record.contains(category)) {
                    System.out.println(record);
                }
            }
            System.out.println();
        }
    }
}
