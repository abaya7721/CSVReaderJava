package learn.library.view;

import learn.library.domain.RecordService;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

public class View {
    private final RecordService recordService;

    public View(String path) {
        this.recordService = new RecordService(path);
    }

    public void showAllRecords() {
        List<String> records = recordService.getAllRecords();
        System.out.println("ALL EXPENSE RECORDS");
        for (String record : records) {
            System.out.println(record);
        }
    }

    public void showCategoriesRecords() {
        List<String> categories = recordService.getRecordsByCategory();
        String header = recordService.getAllRecords().getFirst();
        header = header.replace("Category,", "");
        List<String> records = recordService.getAllRecords();

        for (String category : categories) {
            System.out.println("FILTERED\nExpense record: " + category.toUpperCase());
            System.out.println(header);
            for (String record : records) {
                if (record.contains(category)) {
                    record = record.replace(category+",", "");
                    System.out.println(record);
                }
            }
            System.out.println();
        }
    }

    public void showDateRecords() {
        List<LocalDate> dateRecords = recordService.getDateRecords();
        System.out.println("ALL DATE RECORDS");
        for (LocalDate date : dateRecords) {
            System.out.println(date);
        }
    }
}
