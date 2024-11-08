package interview.highnote_3;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class TCCExample {
    private final Map<String, Integer> accountBalance = new HashMap<>();
    private final Map<String, ReentrantLock> locks = new HashMap<>();

    // Try phase
    public void tryOperation(String account, int amount) {
        ReentrantLock lock = getLock(account);
        lock.lock();
        try {
            // Check if there are sufficient funds for the transaction
            if (accountBalance.containsKey(account) && accountBalance.get(account) >= amount) {
                // Deduct the amount from the account balance (simulate a reservation)
                //int newBalance = accountBalance.get(account) - amount;
                //accountBalance.put(account, newBalance);
                System.out.println("Try: Reservation made for " + amount + " on account " + account);
            } else {
                throw new RuntimeException("Insufficient funds");
            }
        } finally {
            lock.unlock();
        }
    }

    // Commit phase
    public void commitOperation(String account, int amount) {
        ReentrantLock lock = getLock(account);
        lock.lock();
        try {
            // Update the actual account balance
            int newBalance = accountBalance.get(account) - amount;
            accountBalance.put(account, newBalance);
            System.out.println("Commit: Transaction committed for " + amount + " on account " + account);
        } finally {
            lock.unlock();
        }
    }

    // Cancel phase (compensating action)
    public void cancelOperation(String account, int amount) {
        ReentrantLock lock = getLock(account);
        lock.lock();
        try {
            // Rollback the reservation made in the Try phase
            int newBalance = accountBalance.get(account) + amount;
            accountBalance.put(account, newBalance);
            System.out.println("Cancel: Transaction cancelled for " + amount + " on account " + account);
        } finally {
            lock.unlock();
        }
    }

    private ReentrantLock getLock(String account) {
        locks.putIfAbsent(account, new ReentrantLock());
        return locks.get(account);
    }

    public static void main(String[] args) {
        TCCExample tcc = new TCCExample();

        // Simulate concurrent TCC operations
        String account = "A123";
        int amount = 100;
        int numThreads = 5;

        for (int i = 0; i < numThreads; i++) {
            new Thread(() -> {
                try {
                    tcc.tryOperation(account, amount);
                    // Perform other Try phase actions here

                    // If Try phase succeeds, then Commit
                    tcc.commitOperation(account, amount);

                    // If Commit phase succeeds, the operation is complete
                    System.out.println("Transaction completed successfully");
                } catch (Exception e) {
                    // If any phase fails, perform the Cancel phase
                    tcc.cancelOperation(account, amount);
                    System.out.println("Transaction failed: " + e.getMessage());
                }
            }).start();
        }
    }
}
