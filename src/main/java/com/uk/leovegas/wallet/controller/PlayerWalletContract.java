package com.uk.leovegas.wallet.controller;

import com.uk.leovegas.wallet.entity.Transaction;
import com.uk.leovegas.wallet.entity.Wallet;
import com.uk.leovegas.wallet.requestmodel.PlayerWalletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * This interface defines the API contract definition for the Player wallet service.
 *
 * @author Anoop Abraham
 */
@Api(value = "/api/player/wallet", tags = { "Player Wallet Management" })
public interface PlayerWalletContract {


    /**
     * Method to Create the Wallet for the player.
     *
     * @param playerWalletRequest
     * @return
     */
    @ApiOperation(value = "Add the Wallet to player")
    public ResponseEntity<Wallet> createPlayerWallet(@RequestBody PlayerWalletRequest playerWalletRequest);

    /**
     * Method to fetch the wallet for the player.
     *
     * @param playerId
     * @return
     */
    @ApiOperation(value = "Fetch the player wallet information")
    public ResponseEntity<Wallet> getWallet(@ApiParam(value = "Player Id", required = true) final Long playerId);

    /**
     * Method to add credit to the player wallet.
     *
     * @param playerId
     * @return
     */
    @ApiOperation(value = "Credit to the player wallet")
    public ResponseEntity<Wallet> performCredit(@ApiParam(value = "Player Id", required = true)  final Long playerId,
                                                @RequestBody final Transaction transaction);

    /**
     * Method to perform debit from the player account
     *
     * @param playerId
     * @return
     */
    @ApiOperation(value = "Debit from the player account")
    public ResponseEntity<Wallet> performDebit(@ApiParam(value = "Player Id", required = true)  final Long playerId,
                                               @RequestBody final Transaction transaction);

    /**
     * Method to fetch the transaction history of teh player.
     *
     * @param playerId
     * @return
     */
    @ApiOperation(value = "Fetch the player transaction history")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@ApiParam(value = "Player Id", required = true)  final Long playerId);

}
