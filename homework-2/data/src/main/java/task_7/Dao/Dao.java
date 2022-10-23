package task_7.Dao;

import task_7.Dto.Expense;
import task_7.Dto.Receiver;

import java.util.ArrayList;

public interface Dao {
    Receiver getReceiver(int num);
    ArrayList<Receiver> getReceivers();
    Expense getExpense(int num);
    ArrayList<Expense> getExpenses();
    int addReceiver(Receiver receiver);
    int addExpense(Expense expense);
}
