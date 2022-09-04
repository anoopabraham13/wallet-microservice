package com.uk.leovegas.wallet;

import com.uk.leovegas.wallet.entity.Player;
import com.uk.leovegas.wallet.entity.Transaction;
import com.uk.leovegas.wallet.entity.Wallet;
import com.uk.leovegas.wallet.entity.domain.CurrencyCode;
import com.uk.leovegas.wallet.entity.domain.TransactionType;
import com.uk.leovegas.wallet.exception.InvalidTransactionException;
import com.uk.leovegas.wallet.repository.PlayerRepository;
import com.uk.leovegas.wallet.repository.WalletRepository;
import com.uk.leovegas.wallet.requestmodel.PlayerWalletRequest;
import com.uk.leovegas.wallet.service.PlayerServiceImpl;
import com.uk.leovegas.wallet.service.WalletServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Test cases for WalletServiceImpl class
 *
 * @author  Anoop Abraham
 */
@RunWith(MockitoJUnitRunner.class)
public class WalletServiceImplTest {


    /** Test values. */
    private static final Long TEST_PLAYER_ID = Long.valueOf(1);
    private static final Long TEST_WALLET_ID = Long.valueOf(2);
    private static final Long TEST_AMOUNT = Long.valueOf(5);

    @InjectMocks
    private WalletServiceImpl walletService;

    @Mock
    private PlayerServiceImpl playerService;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private PlayerRepository playerRepository;

    /**
     * Show that a new Wallet for player can be successfully saved.
     */
    @Test
    public void shouldSuccessfullySaveNewWalletForPlayer() {
        Player player = getPlayer();
        PlayerWalletRequest playerWalletRequest = getPlayerWalletRequest();
        given(playerService.getPlayer(isA(Long.class))).willReturn(player);

        given(playerRepository.saveAndFlush(isA(Player.class))).willReturn(player);
        given(walletRepository.saveAndFlush(isA(Wallet.class))).willReturn(getWallet());

        Wallet savedWallet =this.walletService.createPlayerWallet(playerWalletRequest);

        assertEquals("The initial balance of the wallet must be zero",0d , savedWallet.getBalance(), 0d);
        assertEquals("The currency code must be same", savedWallet.getCurrencyCode(),
                playerWalletRequest.getCurrencyCode());
        verify(playerRepository, times(1)).saveAndFlush(isA(Player.class));
        verify(walletRepository, times(1)).saveAndFlush(isA(Wallet.class));
        verifyNoMoreInteractions(playerRepository);
        verifyNoMoreInteractions(walletRepository);
    }

    /**
     * Show that a Wallet of the Player can be successfully retrieved.
     */
    @Test
    public void shouldSuccessfullyGetPlayerWallet() {
        Player player = getPlayerWithWallet();
        given(playerService.getPlayer(isA(Long.class))).willReturn(player);

        Wallet wallet =this.walletService.getWallet(TEST_PLAYER_ID);

        assertNotNull("Wallet should not be null", wallet);
        verifyNoMoreInteractions(playerRepository);
    }

    /**
     * Show that a Wallet of the Player can be successfully credited.
     */
    @Test
    public void shouldSuccessfullyCreditPlayerWallet() {
        Player player = getPlayerWithWallet();
        given(playerService.getPlayer(isA(Long.class))).willReturn(player);
        given(walletRepository.saveAndFlush(isA(Wallet.class))).willReturn(getWallet());
        given(playerRepository.saveAndFlush(isA(Player.class))).willReturn(player);

        Wallet wallet =this.walletService.performCredit(TEST_PLAYER_ID, getCreditTransaction());

        assertNotNull("Wallet should not be null", wallet);
        assertEquals("Wallet should credited with amount", TEST_AMOUNT, wallet.getBalance(), 0d);

        verify(playerRepository, times(1)).saveAndFlush(isA(Player.class));
        verify(walletRepository, times(1)).saveAndFlush(isA(Wallet.class));
        verifyNoMoreInteractions(playerRepository);
        verifyNoMoreInteractions(walletRepository);
    }

    /**
     * Show that an InvalidTransactionException will throw for Invalid CurrencyCode
     */
    @Test(expected = InvalidTransactionException.class)
    public void shouldThrowInvalidTransactionException_ForInvalidCurrencyCode() {
        Player player = getPlayerWithWallet();
        given(playerService.getPlayer(isA(Long.class))).willReturn(player);
        Transaction transaction = getCreditTransaction();
        transaction.setCurrencyCode(CurrencyCode.DOL);
        Wallet wallet =this.walletService.performCredit(TEST_PLAYER_ID, transaction);

        verify(playerRepository, times(0)).saveAndFlush(isA(Player.class));
        verify(walletRepository, times(0)).saveAndFlush(isA(Wallet.class));
        verifyNoMoreInteractions(playerRepository);
        verifyNoMoreInteractions(walletRepository);
    }

    /**
     * Show that an InvalidTransactionException will throw for Invalid TransactionType
     */
    @Test(expected = InvalidTransactionException.class)
    public void shouldThrowInvalidTransactionException_ForInvalidTransactionType() {
        Player player = getPlayerWithWallet();
        given(playerService.getPlayer(isA(Long.class))).willReturn(player);
        Transaction transaction = getCreditTransaction();
        transaction.setTransactionType(TransactionType.DEBIT);
        Wallet wallet =this.walletService.performCredit(TEST_PLAYER_ID, transaction);

        verify(playerRepository, times(0)).saveAndFlush(isA(Player.class));
        verify(walletRepository, times(0)).saveAndFlush(isA(Wallet.class));
        verifyNoMoreInteractions(playerRepository);
        verifyNoMoreInteractions(walletRepository);
    }

    /**
     * Show that an InvalidTransactionException will throw for Invalid Transaction Amount
     */
    @Test(expected = InvalidTransactionException.class)
    public void shouldThrowInvalidTransactionException_ForInvalidTransactionAmount() {
        Player player = getPlayerWithWallet();
        given(playerService.getPlayer(isA(Long.class))).willReturn(player);
        Transaction transaction = getCreditTransaction();
        transaction.setAmount(-TEST_AMOUNT);
        Wallet wallet =this.walletService.performCredit(TEST_PLAYER_ID, transaction);

        verify(playerRepository, times(0)).saveAndFlush(isA(Player.class));
        verify(walletRepository, times(0)).saveAndFlush(isA(Wallet.class));
        verifyNoMoreInteractions(playerRepository);
        verifyNoMoreInteractions(walletRepository);
    }


    /**
     * Show that an InvalidTransactionException will throw for Insufficient Balance For Debit.
     */
    @Test(expected = InvalidTransactionException.class)
    public void shouldThrowInvalidTransactionException_ForInsufficientBalanceForDebit() {
        Player player = getPlayerWithWallet();
        given(playerService.getPlayer(isA(Long.class))).willReturn(player);
        Transaction transaction = getDebitTransaction();
        transaction.setAmount(TEST_AMOUNT);
        Wallet wallet =this.walletService.performCredit(TEST_PLAYER_ID, transaction);

        verify(playerRepository, times(0)).saveAndFlush(isA(Player.class));
        verify(walletRepository, times(0)).saveAndFlush(isA(Wallet.class));
        verifyNoMoreInteractions(playerRepository);
        verifyNoMoreInteractions(walletRepository);
    }

    /**
     * Show that a Wallet of the Player can be successfully debit.
     */
    @Test
    public void shouldSuccessfullyDebitPlayerWallet() {
        Player player = getPlayerWithWallet();
        player.getWallet().setBalance(10d);
        given(playerService.getPlayer(isA(Long.class))).willReturn(player);
        given(walletRepository.saveAndFlush(isA(Wallet.class))).willReturn(getWallet());
        given(playerRepository.saveAndFlush(isA(Player.class))).willReturn(player);

        Wallet wallet =this.walletService.performDebit(TEST_PLAYER_ID, getDebitTransaction());

        assertNotNull("Wallet should not be null", wallet);
        assertEquals("Wallet should debited with amount", TEST_AMOUNT, wallet.getBalance(), 0d);

        verify(playerRepository, times(1)).saveAndFlush(isA(Player.class));
        verify(walletRepository, times(1)).saveAndFlush(isA(Wallet.class));
        verifyNoMoreInteractions(playerRepository);
        verifyNoMoreInteractions(walletRepository);
    }


    /**
     * Method to get an Player instance
     *
     * @return
     */
    private Player getPlayer(){
        return Player.builder().id(TEST_PLAYER_ID).build();
    }

    /**
     * Method to get an Player with Wallet instance
     *
     * @return
     */
    private Player getPlayerWithWallet(){
        return Player.builder().id(TEST_PLAYER_ID).wallet(getWallet()).transactions(new ArrayList<>()).build();
    }

    /**
     * Method to get an Wallet instance
     *
     * @return
     */
    private Wallet getWallet(){
        return Wallet.builder().id(TEST_WALLET_ID).balance(0d).currencyCode(CurrencyCode.GBP).build();
    }

    /**
     * Method to get a debit Transaction instance
     *
     * @return
     */
    private Transaction getDebitTransaction(){
        return Transaction.builder().amount(TEST_AMOUNT).currencyCode(CurrencyCode.GBP).transactionType(TransactionType.DEBIT).build();
    }

    /**
     * Method to get a credit Transaction instance
     *
     * @return
     */
    private Transaction getCreditTransaction(){
        return Transaction.builder().amount(TEST_AMOUNT).currencyCode(CurrencyCode.GBP).transactionType(TransactionType.CREDIT).build();
    }

    /**
     * Method to get a PlayerWalletRequest instance
     *
     * @return
     */
    private PlayerWalletRequest getPlayerWalletRequest(){
        return PlayerWalletRequest.builder().playerId(TEST_PLAYER_ID).currencyCode(CurrencyCode.GBP).build();
    }

}
