package model.dto;

public record TransferRequest(
        Integer fromAccountId,
        Integer toAccountId,
        double amount
) {
}
