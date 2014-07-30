package test.puzikov.models;

import java.util.List;

public class OutputJSONContainer {
    private String processTime;
    private List<Transaction> transactions;

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
