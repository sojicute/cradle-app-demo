package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Road;
import com.sojicute.cradle.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadServiceImpl implements RoadService {

    @Autowired
    private RoadRepository roadRepository;

    public RoadServiceImpl(){
    }

    @Override
    public List<Road> findAll() {
        return roadRepository.findAll();
    }

    @Override
    public Road findRoadById(Long id) {
        return roadRepository.findById(id).get();
    }

    @Override
    public Road updateRoadById(Long id, Road road) {
        return roadRepository.save(road);
    }

    @Override
    public void deleteRoadById(Long id) {
        roadRepository.deleteById(id);
    }
}
