package az.dev.useraccountsapplication.entity;



import javax.persistence.*;


@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    private String accountNumber;
    private Double balance;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
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

    public AccountEntity(Long id, UserEntity userId, String accountNumber, Double balance) {
        this.id = id;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public AccountEntity() {

    }

}
