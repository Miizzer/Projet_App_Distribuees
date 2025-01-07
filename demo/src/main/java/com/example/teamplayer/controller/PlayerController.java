package com.example.teamplayer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.teamplayer.entity.Player;
import com.example.teamplayer.entity.Team;
import com.example.teamplayer.repository.PlayerRepository;
import com.example.teamplayer.repository.TeamRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    // Afficher tous les joueurs avec leurs équipes
    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // Ajouter un joueur à une équipe
    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        // Vérifier que l'équipe existe
        if (player.getTeam() != null) {
            Team team = teamRepository.findById(player.getTeam().getId())
                    .orElseThrow(() -> new RuntimeException("Team not found"));
            player.setTeam(team);
        }
        return playerRepository.save(player);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player updatedPlayer) {
        return playerRepository.findById(id)
                .map(player -> {
                    player.setName(updatedPlayer.getName());
                    playerRepository.save(player);
                    return ResponseEntity.ok(player);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        return playerRepository.findById(id).map(player -> {
                playerRepository.delete(player);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

}
