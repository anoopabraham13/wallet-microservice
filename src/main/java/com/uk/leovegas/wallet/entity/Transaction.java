package com.uk.leovegas.wallet.entity;

import com.uk.leovegas.wallet.entity.domain.CurrencyCode;
import com.uk.leovegas.wallet.entity.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The Transaction entity
 *
 * @author Anoop Abraham
 *
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    /**
     * The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    @SequenceGenerator(name = "transaction_sequence", sequenceName = "transaction_sequence", allocationSize = 1)
    private Long id;

    /**
     * The CurrencyCode.
     */
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    /**
     * The Transaction Type.
     */
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    /**
     * The Amount.
     */
    private Long amount;

    /**
     * The Transaction date.
     */
    private LocalDateTime transactionDate;

}
