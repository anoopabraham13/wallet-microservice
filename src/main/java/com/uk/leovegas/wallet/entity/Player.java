package com.uk.leovegas.wallet.entity;

import com.uk.leovegas.wallet.entity.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * The Player entity
 *
 * @author Anoop Abraham
 *
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {


    /**
     * The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_sequence")
    @SequenceGenerator(name = "player_sequence", sequenceName = "player_sequence", allocationSize = 1)
    private Long id;

    /**
     * Player first Name
     */
    private String firstName;

    /**
     * Player middle Name
     */
    private String middleName;

    /**
     * Player last Name
     */
    private String lastName;

    /**
     * Player date Of Birth
     */
    private java.sql.Date dateOfBirth;

    /**
     * Player gender
     */
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * Player wallet
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Wallet wallet;

    /**
     * Player Transactions
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "player_id")
    private List<Transaction> transactions;

}
