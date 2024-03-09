package com.dd.api.restapi.repositories;

import com.dd.api.restapi.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
