import model.Transaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionMapper {
    public Map<String, Double> getAverageAmountPerEmailAddress(List<Transaction> transactions) {

        Map<String, Double> averageTransactionPerEmail = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getEmailAddress,
                        Collectors.averagingDouble(Transaction::getAmount)));

        return averageTransactionPerEmail;
    }

}
