package com.dd.api.restapi.repositories;

import com.dd.api.restapi.models.Pitcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitcherRepository extends JpaRepository<Pitcher, Long> {
}
