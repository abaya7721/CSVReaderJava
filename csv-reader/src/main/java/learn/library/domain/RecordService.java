package learn.library.domain;

import learn.library.data.FileManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordService {
    private final FileManager fileManager;
    List<RecordDataAccess> recordData = new ArrayList<>();

    public RecordService(String path) {
        fileManager = new FileManager(path);
    }

    public List<String> getAllRecords() {
        List<String> records = fileManager.readFile();
        records.removeFirst();
        return records;
    }

    public String getOriginalHeader() {
        return fileManager.readFile().removeFirst();
    }

    public String getHeader() {
        String header = fileManager.readFile().removeFirst();
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

    public boolean validateExpenseRecord(LocalDate date, String category, String description, BigDecimal amount, String paymentMethod) {

        BigDecimal zero = new BigDecimal("0.00");
        String dateString;
        String recordBuilder = "";
        boolean invalid = false;

        if (date.isAfter(LocalDate.now())) {
            System.out.println("Date cannot be future date.");
        } else {
            dateString = date.toString();
            recordBuilder = dateString;
        }

        if (category.isEmpty()) {
            System.out.println("Category cannot be empty.");
            return invalid;
        } else {
            recordBuilder = recordBuilder + "," + category;
        }

        if (description.isEmpty()) {
            System.out.println("Description cannot be empty.");
            return invalid;
        } else {
            recordBuilder = recordBuilder + "," + description;
        }

        if (amount == null) {
            System.out.println("Amount cannot be empty.");
        } else if (amount.compareTo(zero) < 0.00) {
            System.out.println("Amount cannot be less than 0.00.");
            return invalid;

        } else if (amount != null && amount.compareTo(zero) > 0.00) {
            amount = amount.setScale(2, RoundingMode.HALF_UP);
            recordBuilder = recordBuilder + "," + amount;
        }

        if (paymentMethod.isEmpty()) {
            System.out.println("Payment method cannot be empty.");
            return invalid;
        } else {
            recordBuilder = recordBuilder + "," + paymentMethod;

            if (recordBuilder.isEmpty()) {
                System.out.println("Record is empty");
                return invalid;
            }
            System.out.println(recordBuilder);
            return true;
        }
    }

    public String createExpenseRecord() {
        LocalDate newDate = LocalDate.of(2024, 4, 1);
        String category = "Travel";
        String description = "Taxi ride";
        BigDecimal amount = new BigDecimal("17.88");
        String paymentMethod = "Cash";

        if (validateExpenseRecord(newDate, category, description, amount, paymentMethod)) {
            return "Good";
        }
        return "Bad record";
    }


    public void addExpenseRecord() {


    }


}

