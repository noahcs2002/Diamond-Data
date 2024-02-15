package com.dd.api.restapi.repositories;

import com.dd.api.restapi.controllers.DefensivePlayerController;
import com.dd.api.restapi.models.DefensivePlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefensivePlayerRepository extends JpaRepository<DefensivePlayer, Long> {
}
