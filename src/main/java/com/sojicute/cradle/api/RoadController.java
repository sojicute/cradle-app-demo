package com.sojicute.cradle.api;

import com.sojicute.cradle.domain.Road;
import com.sojicute.cradle.services.RoadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RoadController {

    @Autowired
    private RoadServiceImpl roadService;

    public RoadController() {

    }

    @GetMapping("/road")
    public ResponseEntity<List<Road>> getAllRoads() {
        List<Road> roads = roadService.findAll();

        if (roads.isEmpty() && roads == null ) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roads, HttpStatus.OK);
    }

    @GetMapping("/road/{id}")
    public ResponseEntity<Road> getRoad(@PathVariable("id") long id) {
        Road road = roadService.findRoadById(id);
        return new ResponseEntity<>(road, HttpStatus.OK);
    }

    @PostMapping("/road")
    public ResponseEntity<Road> saveRoad(@RequestBody Road road){
        Road createdRoad = roadService.addNewRoad(road);
        return new ResponseEntity<>(createdRoad, HttpStatus.CREATED);
    }

    @PutMapping("/road/{id}")
    public ResponseEntity<Road> updateRoad(@PathVariable("id") long id, @RequestBody Road road ) {
        Road updatedRoad = roadService.updateRoadById(id, road);
        return new ResponseEntity<>(updatedRoad, HttpStatus.OK);
    }

    @DeleteMapping("/road/{id}")
    public ResponseEntity<Void> deleteRoad(@PathVariable("id") long id) {
        roadService.deleteRoadById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
