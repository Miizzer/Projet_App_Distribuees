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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.teamplayer.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    // Afficher toutes les équipes avec leurs joueurs
    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Ajouter une nouvelle équipe
    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }

    // Modifier une team par son id
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team updatedTeam) {
        return teamRepository.findById(id)
                .map(team -> {
                    team.setName(updatedTeam.getName());
                    teamRepository.save(team);
                    return ResponseEntity.ok(team);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Autowired
    private TeamService teamService;

    // Supprimer une team par son id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

}
