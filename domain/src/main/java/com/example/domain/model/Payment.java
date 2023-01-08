package com.example.domain.model;

import com.example.domain.model.type.Currency;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

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

  private Timestamp timestamp;

  private Double amount;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Column(name = "IBAN_TO")
  private String ibanTo;

  @Column(name = "IBAN_FROM")
  private String ibanFrom;
}
