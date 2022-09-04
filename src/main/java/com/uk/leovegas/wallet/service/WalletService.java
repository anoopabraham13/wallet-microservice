package com.uk.leovegas.wallet.service;

import com.uk.leovegas.wallet.entity.Transaction;
import com.uk.leovegas.wallet.entity.Wallet;
import com.uk.leovegas.wallet.requestmodel.PlayerWalletRequest;

import java.util.List;

/**
 * Interface for WalletService to handle the player wallet operations.
 *
 * @author Anoop Abraham
 *
 */
public interface WalletService {

    /**
     * Method to Create the Wallet for the player.
     *
     * @param playerWalletRequest
     * @return
     */
    public Wallet createPlayerWallet(PlayerWalletRequest playerWalletRequest);

    /**
     * Method to fetch the wallet for the player.
     *
     * @param playerId
     * @return
     */
    public Wallet getWallet(final Long playerId);

    /**
     * Method to add credit to the player wallet.
     *
     * @param playerId
     * @return
     */
    public Wallet performCredit(final Long playerId, final Transaction transaction);

    /**
     * Method to perform debit from the player account
     *
     * @param playerId
     * @return
     */
    public Wallet performDebit(final Long playerId, final Transaction transaction);

    /**
     * Method to fetch the transaction history of teh player.
     *
     * @param playerId
     * @return
     */
    public List<Transaction> getTransactionHistory(final Long playerId);

}
