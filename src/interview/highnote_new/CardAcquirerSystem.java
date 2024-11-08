package interview.highnote_new;

import java.util.*;
import java.util.concurrent.*;

class CardHolder {
    private String cardNumber;
    private double balance;

    public CardHolder(String cardNumber, double initialBalance) {
        this.cardNumber = cardNumber;
        this.balance = initialBalance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void addFunds(double amount) {
        balance += amount;
    }

    public void deductFunds(double amount) {
        balance -= amount;
    }
}

class Merchant {
    private String merchantId;

    public Merchant(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantId() {
        return merchantId;
    }
}

class Transaction {
    private String transactionId;
    private Merchant merchant;
    private String cardNumber;
    private double amount;

    public Transaction(String transactionId, Merchant merchant, String cardNumber, double amount) {
        this.transactionId = transactionId;
        this.merchant = merchant;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getAmount() {
        return amount;
    }
}

class FailedTransaction {
    private Transaction transaction;
    private int retryCount;

    public FailedTransaction(Transaction transaction, int retryCount) {
        this.transaction = transaction;
        this.retryCount = retryCount;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public int getRetryCount() {
        return retryCount;
    }
}

public class CardAcquirerSystem {
    private Map<String, CardHolder> cardholders = new HashMap<>();
    private Map<String, Merchant> merchants = new HashMap<>();
    private List<FailedTransaction> failedTransactions = new ArrayList<>();
    private Map<String, Transaction> transactionLog = new ConcurrentHashMap<>(); // Use ConcurrentHashMap for thread-safety
    private String commitLog = "commit-log.txt"; // File to store commit-log
    private int maxRetryCount = 3; // Maximum number of retries allowed
    private ExecutorService executorService;
    private Object lock = new Object(); // Synchronization lock for rollback

    public CardAcquirerSystem() {
        executorService = Executors.newFixedThreadPool(5); // Adjust the number of threads as needed
    }

    public void addCardHolder(String cardNumber, double initialBalance) {
        CardHolder cardHolder = new CardHolder(cardNumber, initialBalance);
        cardholders.put(cardNumber, cardHolder);
    }

    public void addMerchant(String merchantId) {
        Merchant merchant = new Merchant(merchantId);
        merchants.put(merchantId, merchant);
    }

    public boolean authorizeTransaction(Transaction transaction, int retryCount) {
        String cardNumber = transaction.getCardNumber();
        double amount = transaction.getAmount();

        if (cardholders.containsKey(cardNumber)) {
            CardHolder cardHolder = cardholders.get(cardNumber);

            if (cardHolder.getBalance() >= amount) {
                // Simulate writing to commit-log before executing the transaction
                writeCommitLog(transaction);

                // Execute the transaction atomically
                synchronized (lock) {
                    try {
                        cardHolder.deductFunds(amount); // Deduct funds
                        transactionLog.put(transaction.getTransactionId(), transaction);
                        System.out.println("Transaction authorized and processed.");
                        return true;
                    } catch (Exception e) {
                        // Handle exceptions during transaction execution (e.g., rollback)
                        System.out.println("Transaction execution failed: " + e.getMessage());
                        rollbackTransaction(transaction);
                        return false;
                    }
                }
            } else {
                System.out.println("Transaction denied due to insufficient funds.");
                if (retryCount < maxRetryCount) {
                    System.out.println("Retrying transaction...");
                    retryCount++;
                    return authorizeTransaction(transaction, retryCount);
                } else {
                    System.out.println("Max retry limit reached. Transaction failed.");
                    failedTransactions.add(new FailedTransaction(transaction, retryCount));
                    return false;
                }
            }
        } else {
            System.out.println("Card not found.");
            return false;
        }
    }

    private void writeCommitLog(Transaction transaction) {
        // Simulate writing the transaction to the commit-log (e.g., append to a file)
        String logEntry = transaction.getTransactionId() + "," + transaction.getCardNumber() + "," + transaction.getAmount();
        // Implement actual commit-log write logic (e.g., using FileWriter)
        System.out.println("Writing to commit-log: " + logEntry);
    }

    private void rollbackTransaction(Transaction transaction) {
        // Implement rollback logic (e.g., add funds back to the cardholder's balance)
        String cardNumber = transaction.getCardNumber();
        double amount = transaction.getAmount();
        CardHolder cardHolder = cardholders.get(cardNumber);
        cardHolder.addFunds(amount);
        System.out.println("Transaction rolled back.");
    }

    public void processFailedTransactions() {
        System.out.println("Processing failed transactions from commit-log...");
        // Implement recovery logic using the commit-log (e.g., read and process transactions)
        // hashmap is to simulate the log

        System.out.println("Processing failed transactions...");
        for (FailedTransaction failedTransaction : failedTransactions) {
            Transaction transaction = failedTransaction.getTransaction();
            int retryCount = failedTransaction.getRetryCount();

            // Implement your logic for processing failed transactions here
            System.out.println("Transaction ID: " + transaction.getTransactionId() + " failed after " + retryCount + " retries.");
            // You can choose to retry the failed transaction, log it, or take other actions.
        }
        System.out.println("Finished processing failed transactions!");
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public Future<?> submitTransaction(Transaction transaction) {
        Future<?> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                authorizeTransaction(transaction,0);
            }
        });
        return future;
    }

    public static void main(String[] args) throws Exception{
        CardAcquirerSystem acquirer = new CardAcquirerSystem();

        acquirer.addCardHolder("1234567890123456", 1000.0);
        acquirer.addMerchant("Merchant123");

        // Example transaction authorization with retry
        String cardNumber = "1234567890123456";
        String merchantId = "Merchant123";
        double transactionAmount = 1500.0; // This amount exceeds the balance

        Transaction transaction1 = new Transaction(UUID.randomUUID().toString(), acquirer.merchants.get(merchantId), cardNumber, transactionAmount);
        Transaction transaction2 = new Transaction(UUID.randomUUID().toString(), acquirer.merchants.get(merchantId), cardNumber, 200.0);

        // Submit transactions concurrently
        Future<?> future1 = acquirer.submitTransaction(transaction1);
        Future<?> future2 = acquirer.submitTransaction(transaction2);
        future1.get(); //this blocks
        future2.get();
        // Shutdown the executor service after processing all transactions
        acquirer.shutdown();

        // Process failed transactions
        acquirer.processFailedTransactions();
    }
}
