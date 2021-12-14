package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Road;
import com.sojicute.cradle.domain.User;
import com.sojicute.cradle.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Security;
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
        return roadRepository.findById(id).get();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #road.getUser().getUsername()")
    @Override
    public Road addNewRoad(Road road) {
        return roadRepository.save(road);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #road.getUser().getUsername()")
    @Override
    public Road updateRoadById(Long id, Road road) {
        return roadRepository.save(road);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteRoadById(Long id) {
        roadRepository.deleteById(id);
    }

}
