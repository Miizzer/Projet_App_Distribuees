package com.example.teamplayer.service;

import com.example.teamplayer.entity.Player;
import com.example.teamplayer.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Récuperer tous les joueurs de la base de données
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // Sauvegarder un joueur dans la base de données
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    // Supprimer un joueur de la base de données
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
