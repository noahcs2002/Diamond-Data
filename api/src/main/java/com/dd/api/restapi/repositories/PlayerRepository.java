package com.dd.api.restapi.repositories;

import com.dd.api.restapi.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}