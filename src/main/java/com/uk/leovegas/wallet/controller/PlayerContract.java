package com.uk.leovegas.wallet.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.uk.leovegas.wallet.entity.Player;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * This interface defines the API contract definition for the Player service.
 *
 * @author Anoop Abraham
 */
@Api(value = "/api/player", tags = { "Player Management" })
public interface PlayerContract {


    /**
     * Method to Create the Player Entity.
     *
     * @param player
     * @return
     */
    @ApiOperation(value = "Create a new Player entity")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player);

    /**
     * Method to Fetch the Player for the id.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Fetch the Player for the id")
    public ResponseEntity<Player> getPlayer(@ApiParam(value = "Player id", required = true) Long id);


    /**
     * Method to Fetch all the Players.
     *
     * @return
     */
    @ApiOperation(value = "Fetch all the Players")
    public ResponseEntity<List<Player>> getAllPlayers();

}
