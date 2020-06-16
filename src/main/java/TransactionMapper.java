import model.Transaction;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionMapper {
    public Map<String, Double> getAverageAmountPerEmailAddress(List<Transaction> transactions) {

        Map<String, List<Transaction>> transactionPerEmail = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getEmailAddress));

        Map<String, Double> averageTransactionPerEmail = transactionPerEmail.entrySet().stream()
                .map(entry1 -> {
                    String email = entry1.getKey();
                    List<Transaction> personTransactions = entry1.getValue();

                    double average = personTransactions.stream()
                            .mapToDouble(Transaction::getAmount)
                            .average()
                            .getAsDouble();

                    return new SimpleEntry<>(email, average);
                })
                .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

        return averageTransactionPerEmail;
    }

}
