package az.dev.useraccountsapplication.dto.request;

import java.io.Serializable;

public class AccountRequest implements Serializable {

    private Long userId;
    private String accountNumber;
    private Double balance;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountRequest(Long userId, String accountNumber, Double balance) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public AccountRequest() {

    }
}
