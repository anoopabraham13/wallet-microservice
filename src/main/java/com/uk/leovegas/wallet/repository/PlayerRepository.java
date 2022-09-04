package com.uk.leovegas.wallet.repository;


import com.uk.leovegas.wallet.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing Player entity directly from database.
 *
 * @author Anoop Abraham
 *
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
