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

        for(String record : records) {
            System.out.println(record);
        }
    }
}
