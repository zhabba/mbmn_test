package test.puzikov.storage;

import test.puzikov.models.ServiceXML;
import test.puzikov.models.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentTransactionStorage {

    public static ConcurrentMap<String, List<ServiceXML>> storage = new ConcurrentHashMap<>();

    private ConcurrentTransactionStorage() {
    }

    private static class LazyConcurrentTransactionStorageHolder {
        private static final ConcurrentTransactionStorage INSTANCE = new ConcurrentTransactionStorage();
    }

    public static ConcurrentTransactionStorage getInstance() {
        return LazyConcurrentTransactionStorageHolder.INSTANCE;
    }

    public void addToStorage(String login, ServiceXML transaction) {
        List<ServiceXML> transactions = storage.get(login);
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
        storage.put(login, transactions);
    }

    public List<Transaction> getAllTransactionsByLogin(String login) {
        List<ServiceXML> transactionsByLogin = storage.get(login);
        List<Transaction> transactions = new ArrayList<>();
        if (transactionsByLogin != null) {
            for (ServiceXML s : transactionsByLogin) {
                transactions.add(s.getTransaction());
            }
            Collections.sort(transactions, new Comparator<Transaction>() {
                @Override
                public int compare(Transaction t1, Transaction t2) {
                    return t1.getTimeStamp().compareTo(t2.getTimeStamp());
                }
            });

        } else {
            transactions = new ArrayList<>();
        }
        return transactions;
    }

    public void clear() {
        storage.clear();
    }
}
