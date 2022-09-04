package com.uk.leovegas.wallet.repository;


import com.uk.leovegas.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing Wallet entity directly from database.
 *
 * @author Anoop Abraham
 *
 */
public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
