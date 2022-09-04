package com.uk.leovegas.wallet;

import com.uk.leovegas.wallet.entity.Player;
import com.uk.leovegas.wallet.repository.PlayerRepository;
import com.uk.leovegas.wallet.service.PlayerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Test cases for PlayerServiceImpl class
 *
 * @author  Anoop Abraham
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceImplTest {


    /** Test values. */
    private static final Long TEST_PLAYER_ID = Long.valueOf(1);

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Mock
    PlayerRepository playerRepository;

    /**
     * Show that a new Player can be successfully saved.
     */
    @Test
    public void shouldSuccessfullySaveNewPlayer() {
        Player player = getPlayer();
        given(playerRepository.saveAndFlush(isA(Player.class))).willReturn(player);

        this.playerService.createPlayer(player);

        verify(playerRepository, times(1)).saveAndFlush(isA(Player.class));
        verifyNoMoreInteractions(playerRepository);
    }

    /**
     * Show that a Player can be successfully retrieved.
     */
    @Test
    public void shouldSuccessfullyGetAPlayer() {
        given(playerRepository.findById(isA(Long.class))).willReturn(Optional.of(getPlayer()));

        Player playerResponse = this.playerService.getPlayer(TEST_PLAYER_ID);

        assertNotNull("response should not be null", playerResponse);
        assertEquals("ID's doesn't match", TEST_PLAYER_ID, playerResponse.getId());

        verify(playerRepository, times(1)).findById(isA(Long.class));
        verifyNoMoreInteractions(playerRepository);
    }

    /**
     * Show that all the Players can be successfully retrieved.
     */
    @Test
    public void shouldSuccessfullyGetAllPlayer() {
        List<Player> players = new ArrayList<>();
        players.add(getPlayer());
        given(playerRepository.findAll()).willReturn(players);

        List<Player> playerList = this.playerService.getAllPlayers();

        assertNotNull("response should not be null", playerList);
        assertEquals("Size doesn't match", 1, playerList.size());

        verify(playerRepository, times(1)).findAll();
        verifyNoMoreInteractions(playerRepository);
    }

    /**
     * Method to get an player instance
     *
     * @return
     */
    private Player getPlayer(){
        return Player.builder().id(TEST_PLAYER_ID).build();
    }

}
