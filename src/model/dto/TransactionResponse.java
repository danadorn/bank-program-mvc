package model.dto;

import java.sql.Timestamp;
import java.util.Locale;

public record TransactionResponse(
        int id,
        String senderName,
        String receiverName,
        double amount,
        String timestamp

) {
}
