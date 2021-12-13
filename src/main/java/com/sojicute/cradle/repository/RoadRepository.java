package com.sojicute.cradle.repository;

import com.sojicute.cradle.domain.Road;
import com.sojicute.cradle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadRepository extends JpaRepository<Road, Long> {
    List<Road> findRoadsByUser_Username(String username);
}
