package com.uk.leovegas.wallet.entity;

import com.uk.leovegas.wallet.entity.domain.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    /**
     * The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_sequence")
    @SequenceGenerator(name = "player_sequence", sequenceName = "player_sequence", allocationSize = 1)
    private Long id;

    /**
     * Player wallet balance
     */
    private Double balance;

    /**
     * Player wallet currency Code
     */
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

}
