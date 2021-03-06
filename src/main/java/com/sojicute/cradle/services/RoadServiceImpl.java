package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Road;
import com.sojicute.cradle.exception.ResourceNotFoundException;
import com.sojicute.cradle.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RoadServiceImpl implements RoadService {

    @Autowired
    private RoadRepository roadRepository;

    public RoadServiceImpl(){
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    public List<Road> findAll() {
        return roadRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    public Road findRoadById(Long id) {
        Road road = roadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Road with id = " + id));
        return road;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #road.getUser().getUsername()")
    @Override
    public Road addNewRoad(Road road) {
        return roadRepository.save(road);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #road.getUser().getUsername()")
    @Override
    public Road updateRoadById(Long id, Road road) {
        Road _road = roadRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found Road with id =" + id));

        _road.setTitle(road.getTitle());
        _road.setDescription(road.getDescription());
        return roadRepository.save(road);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteRoadById(Long id) {
        roadRepository.deleteById(id);
    }

}
