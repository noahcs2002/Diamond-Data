package com.dd.api.restapi.repositories;

import com.dd.api.restapi.models.OffensivePlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffensivePlayerRepository extends JpaRepository<OffensivePlayer, Long> {
}
