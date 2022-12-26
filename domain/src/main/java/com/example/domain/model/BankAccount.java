package com.example.domain.model;

import com.example.domain.model.type.Currency;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iban;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Double amount = 0.0;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
