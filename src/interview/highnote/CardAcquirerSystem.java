package interview.highnote;

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
    private int maxRetryCount = 3; // Maximum number of retries allowed
    private ExecutorService executorService;

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
                cardHolder.addFunds(-amount); // Deduct funds
                System.out.println("Transaction " + transaction.getTransactionId() + " authorized and processed.");
                return true;
            } else {
                System.out.println("Transaction " + transaction.getTransactionId() +  " denied due to insufficient funds.");
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

    public void processFailedTransactions() {
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

    public static void main(String[] args) throws Exception {
        CardAcquirerSystem acquirer = new CardAcquirerSystem();

        acquirer.addCardHolder("1234567890123456", 1000.0);
        acquirer.addMerchant("Merchant123");

        // Example transaction authorization with retry
        String cardNumber = "1234567890123456";
        String merchantId = "Merchant123";
        double transactionAmount = 1500.0; // This amount exceeds the balance

        Transaction transaction1 = new Transaction("12345", acquirer.merchants.get(merchantId), cardNumber, transactionAmount);
        Transaction transaction2 = new Transaction("12346", acquirer.merchants.get(merchantId), cardNumber, 200.0);

        // Submit transactions concurrently
        Future<?> future1 = acquirer.submitTransaction(transaction1);
        Future<?> future2 = acquirer.submitTransaction(transaction2);

        future1.get();
        future2.get();

        // Process failed transactions
        acquirer.processFailedTransactions();

        // Shutdown the executor service after processing all transactions
        acquirer.shutdown();

    }
}





