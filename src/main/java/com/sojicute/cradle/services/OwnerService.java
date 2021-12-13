package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Road;

import java.util.List;

public interface OwnerService {
    List<Road> findOwnerRoads(String username);
    Road findOwnerRoadById(String username, Long id);

}
