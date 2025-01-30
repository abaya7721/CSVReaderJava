package learn.library.domain;

import learn.library.data.FileManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordService {
    private final FileManager reader;
    List<RecordDataAccess> recordData = new ArrayList<>();

    public RecordService(String path) {
        reader = new FileManager(path);
    }

    

    public List<String> getAllRecords() {
        List<String> records = reader.readFile();
        records.removeFirst();
        return records;
    }

    public String getOriginalHeader() {
        return reader.readFile().removeFirst();
    }

    public String getHeader() {
        String header = reader.readFile().removeFirst();
        String amount = "Amount";
        header = header.replace("Payment Method", amount);
        header = header.replaceFirst(amount, "Payment-Method");
        header = header.replace(",", "  ");
        return header;
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

    public List<BigDecimal> getExpenses() {
        List<String> records = getAllRecords();
        List<BigDecimal> expenses = new ArrayList<>();
        for (String record : records) {
            String[] line = record.split(",");
            String currencyField = line[3];
            expenses.add(new BigDecimal(currencyField).setScale(2, RoundingMode.HALF_UP));
        }
        return expenses;
    }

    public List<String> removeDates() {
        List<String> recordsNoDates = new ArrayList<>();

        for (String record : getAllRecords()) {
            List<String> line = new ArrayList<>(Arrays.stream(record.split(",")).toList());
            line.remove(0);
            recordsNoDates.add(String.valueOf(line));
        }
        //System.out.println(recordsNoDates);
        return recordsNoDates;
    }

    public List<String> removeCostAndDates() {
        List<String> recordsExtracted = removeDates();
        List<String> trimmedRecords = new ArrayList<>();

        for (String record : recordsExtracted) {
            List<String> line = new ArrayList<>(Arrays.stream(record.split(",")).toList());
            line.remove(2);
            trimmedRecords.add(String.valueOf(line));
        }
        //System.out.println(recordsNoDates);
        return trimmedRecords;
    }

    public List<RecordDataAccess> dataAccess() {
        List<LocalDate> dates = getDateRecords();
        List<BigDecimal> expensesList = getExpenses();
        List<String> records = removeCostAndDates();

        for (int i = 0; i < records.size(); i++) {
            recordData.add(new RecordDataAccess(dates.get(i), records.get(i).trim(), expensesList.get(i)));
        }

        return recordData;
    }


}

