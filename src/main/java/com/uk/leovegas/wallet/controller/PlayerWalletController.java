package com.uk.leovegas.wallet.controller;

import com.uk.leovegas.wallet.entity.Transaction;
import com.uk.leovegas.wallet.entity.Wallet;
import com.uk.leovegas.wallet.requestmodel.PlayerWalletRequest;
import com.uk.leovegas.wallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/player/wallet")
public class PlayerWalletController implements PlayerWalletContract {

    @Autowired
    private WalletService walletService;

    @Override
    @PostMapping
    public ResponseEntity<Wallet> createPlayerWallet(PlayerWalletRequest playerWalletRequest) {
        log.info("creating player wallet for the playerId: {}", playerWalletRequest.getPlayerId());
        return new ResponseEntity<>(walletService.createPlayerWallet(playerWalletRequest), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(value = "/{playerId}")
    public ResponseEntity<Wallet> getWallet(@PathVariable(name = "playerId", required = true) final Long playerId) {
        log.info("Fetch the wallet for the playerId: {}", playerId);
        return new ResponseEntity<>(walletService.getWallet(playerId), HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/{playerId}/credit")
    public ResponseEntity<Wallet> performCredit(@PathVariable ("playerId") final Long playerId,
                                                @RequestBody final Transaction transaction) {
        log.info("Perform credit to the wallet for the playerId: {}", playerId);
        return new ResponseEntity<>(walletService.performCredit(playerId, transaction), HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/{playerId}/debit")
    public ResponseEntity<Wallet> performDebit(@PathVariable ("playerId") final Long playerId,
                                               @RequestBody final Transaction transaction) {
        log.info("Perform debit to the wallet for the playerId: {}", playerId);
        return new ResponseEntity<>(walletService.performDebit(playerId, transaction), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/{playerId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable ("playerId") final Long playerId) {
        log.info("Fetching all the transactions done for the playerId: {}", playerId);
        return new ResponseEntity<>(walletService.getTransactionHistory(playerId), HttpStatus.OK);
    }

}
