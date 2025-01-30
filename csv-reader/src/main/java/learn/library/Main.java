package learn.library;

import learn.library.domain.RecordService;
import learn.library.view.View;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {
        View view = new View("expense_report.csv");
        //view.showAllRecords();
        System.out.println();
        //view.showCategoriesRecords();
        //view.showDateRecords();
        //view.recordsWithoutDate();
        //view.viewDataAccessible();

        RecordService service = new RecordService("expense_report.csv");
        //System.out.println(service.createExpenseRecord());

        service.createExpenseRecord(LocalDate.of(2024, 4,3), "Food", "Bagels for meeting", new BigDecimal("90"), "Company Card");
    }
}
