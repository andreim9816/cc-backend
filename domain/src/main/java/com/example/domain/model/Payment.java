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
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double amount;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  @ManyToOne
  @JoinColumn(name = "IBAN_TO")
  private BankAccount bankAccountTo;

  @ManyToOne
  @JoinColumn(name = "IBAN_FROM")
  private BankAccount bankAccountFrom;
}
