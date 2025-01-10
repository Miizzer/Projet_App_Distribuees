package com.example.teamplayer.service;

import com.example.teamplayer.entity.Team;
import com.example.teamplayer.repository.TeamRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // Récuperer toutes les équipes
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Sauvegarder une équipe
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    // Supprimer une team
    public void deleteTeam(Long id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id); // Supprime l'équipe et les joueurs associés
        } else {
            throw new RuntimeException("Team with id " + id + " not found.");
        }
    }
}
