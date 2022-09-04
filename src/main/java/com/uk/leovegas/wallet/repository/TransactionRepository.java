package com.uk.leovegas.wallet.repository;

import com.uk.leovegas.wallet.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing Transaction entity directly from database.
 *
 * @author Anoop Abraham
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
