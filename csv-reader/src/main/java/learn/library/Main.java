package learn.library;

import learn.library.view.View;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        View view = new View("C:/Users/abayardo/Documents/java-2024-12-work/GitHub/CSVReaderJava/csv-reader/src/main/java/learn/library/data/expense_report.csv");
        view.showAllRecords();
        System.out.println();
        view.showCategoriesRecords();
    }
}
