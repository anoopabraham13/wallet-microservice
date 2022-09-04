package com.uk.leovegas.wallet.service;

import com.uk.leovegas.wallet.entity.Player;

import java.util.List;

/**
 * Interface for PlayerService to handle the player operations.
 *
 * @author Anoop Abraham
 *
 */
public interface PlayerService {


    /**
     * Method to create the Player entity.
     *
     * @param player
     * @return Player
     */
    public Player createPlayer(Player player) ;

    /**
     * Method to Fetch the Player for the id.
     *
     * @param id
     * @return
     */
    public Player getPlayer(final Long id);

    /**
     * Method to Fetch all the Players.
     *
     * @return
     */
    public List<Player> getAllPlayers();
}
