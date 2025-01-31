package learn.library.view;

import learn.library.domain.RecordDataAccess;
import learn.library.domain.RecordService;

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
        String originalHeader = recordService.getOriginalHeader();
        originalHeader = originalHeader.replace("Category,", "");
        List<String> records = recordService.getAllRecords();

        for (String category : categories) {
            System.out.println("FILTERED\nExpense record for: " + category.toUpperCase());
            System.out.println(originalHeader);
            for (String record : records) {
                if (record.contains(category)) {
                    record = record.replace(category + ",", "");
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

    public void recordsWithoutDate() {
        recordService.removeDates();
    }

    public void viewDataAccessible() {
        System.out.println("Before January 31, 2024");
        System.out.println(recordService.getHeader() + "\n");
        for (RecordDataAccess filterRecords : recordService.dataAccess()) {
            if (filterRecords.getDate().isBefore(LocalDate.of(2024, 1, 31))) {
                System.out.print(filterRecords);
            }
        }
    }
}
