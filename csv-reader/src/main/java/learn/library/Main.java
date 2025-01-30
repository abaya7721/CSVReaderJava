package learn.library;

import learn.library.domain.RecordService;
import learn.library.view.View;


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
        System.out.println(service.createExpenseRecord());
    }
}
