package learn.library.domain;

import learn.library.data.FileOpenReader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordService {
    private final FileOpenReader reader;
    List<RecordDataAccess> recordData = new ArrayList<>();

    public RecordService(String path) {
        reader = new FileOpenReader(path);

    }

    public List<String> getAllRecords() {
        List<String> records = reader.readFile();
        records.removeFirst();
        return records;
    }

    public String getHeader() {
        return getAllRecords().removeFirst();
    }

    public List<String> getRecordsByCategory() {
        List<String> records = getAllRecords();
        List<String> categories = new ArrayList<>();
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

    public List<String> removeDates(){
        List<String> recordsNoDates = new ArrayList<>();

        for (String record : getAllRecords()) {
            List<String> line = new ArrayList<>(Arrays.stream(record.split(",")).toList());
            //line.removeIf(field -> line.contains("\\d{4}-\\d{2}-\\d{2}"));
            //record.replace("\\d{4}-\\d{2}-\\d{2}", "" );
            line.remove(0);
            recordsNoDates.add(String.valueOf(line));
        }
        System.out.println(recordsNoDates);
        return recordsNoDates;
    }

    public void removeCost(){

    }

    public List<RecordDataAccess> dataAccess(){
        List<LocalDate> dates = getDateRecords();
        List<String> expenseRecords = removeDates();

        for(int i = 0; i < dates.size(); i++){
            recordData.add(new RecordDataAccess(dates.get(i), expenseRecords.get(i)));
        }
        System.out.println(recordData.toString());

        return null;
    }



}

