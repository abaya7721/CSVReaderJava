package learn.library.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RecordDataAccess {
    private LocalDate date;
    private String expenseInformation;
    private BigDecimal cost;

    public RecordDataAccess(LocalDate date, String expenseInformation, BigDecimal cost) {
        this.date = date;
        this.expenseInformation = expenseInformation;
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getExpenseCategory() {
        String[] line = expenseInformation.split(", ");
        return line[0];
    }
    public String getExpenseDescription() {
        String[] line = expenseInformation.split(", ");
        return line[1];
    }
    public String getExpensePaymentMethod() {
        String[] line = expenseInformation.split(", ");
        return line[2];
    }

    public void setExpenseInformation(String expenseInformation) {
        this.expenseInformation = expenseInformation;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return date + " " + expenseInformation + " cost= " + cost+"\n";
    }
}
