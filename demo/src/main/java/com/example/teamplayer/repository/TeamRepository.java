package com.example.teamplayer.repository;

import com.example.teamplayer.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
