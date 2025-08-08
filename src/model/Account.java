package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {
    private Integer id;
    private String OwnerName;
    private Double balance;


    public Account(String name, Double balance) {
        this.OwnerName = name;
        this.balance = balance;
    }

    public Account(Integer id, String name, Double balance) {
        this.id = id;
        this.OwnerName = name;
        this.balance = balance;
    }
}
