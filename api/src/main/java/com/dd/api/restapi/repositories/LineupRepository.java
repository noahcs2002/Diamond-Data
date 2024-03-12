package com.dd.api.restapi.repositories;

import com.dd.api.restapi.models.Lineup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineupRepository extends JpaRepository<Lineup, Long> {}
