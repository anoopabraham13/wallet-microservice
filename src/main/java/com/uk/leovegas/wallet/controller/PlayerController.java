package com.uk.leovegas.wallet.controller;


import com.uk.leovegas.wallet.entity.Player;
import com.uk.leovegas.wallet.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/player")
public class PlayerController implements PlayerContract{

    @Autowired
    private PlayerService playerService;

    @Override
    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        log.info("creating player entity");
        return new ResponseEntity<>(playerService.createPlayer(player), HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        log.info("Fetching all the players");
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable(name = "id", required = true) final Long id) {
        log.info("Fetching the player for the id {}", id);
        Player player = playerService.getPlayer(id);
        if (player == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

}
