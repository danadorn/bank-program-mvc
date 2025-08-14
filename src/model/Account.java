package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    private Integer id;
    private String ownerName;
    private Double balance;

    public Account (String ownerName, Double balance){
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
