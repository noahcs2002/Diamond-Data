package com.dd.api.restapi.repositories;

import com.dd.api.restapi.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
