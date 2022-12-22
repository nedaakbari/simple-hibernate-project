package org.coding.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data //getter setter/ toString /NoArgsConstructor  /EqualsAndHashCode  / boolean canEqual
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(updatable = false)
    private long accountNumber;
    @Column(length = 16)
    private long cardNumber;


    //    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDate createAccount; //Data?

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exp_date")
    private Date expireDate;
    private double balance;

    private int cvv2;

//    @Enumerated(EnumType.STRING)
//    private AccountType accType;


    //    @Builder(setterPrefix = "with")
    public Account(double balance) {
        this.accountNumber = randomCard();
        long left = 100000000000000L;
        long right = 1000000000000000L;
        this.cardNumber = left + (long) (Math.random() * (right - left));

        this.balance = balance;
        this.expireDate = generateExpireDate();
        this.cvv2 = createCVV2();

    }

    private Date generateExpireDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 4);
        return cal.getTime();
    }

    private int createCVV2() {
        int min = 1000;
        int max = 10000;
        return (int) ((Math.random() * (max - min)) + min);
    }

    private long randomCard() {
        long leftLimit = 100000000000000L;
        long rightLimit = 1000000000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
