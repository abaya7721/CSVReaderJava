package learn.library;

import learn.library.view.View;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        View view = new View("expense_report.csv");
        view.showAllRecords();
        System.out.println();
        view.showCategoriesRecords();
    }
}
