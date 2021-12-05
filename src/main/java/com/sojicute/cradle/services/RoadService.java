package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Road;

import java.util.List;

public interface RoadService {

    List<Road> findAll();

    Road findRoadById(Long id);

    Road updateRoadById(Long id, Road road);

    void deleteRoadById(Long id);
}
