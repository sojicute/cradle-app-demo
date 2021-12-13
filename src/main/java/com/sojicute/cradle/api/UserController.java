package com.sojicute.cradle.api;

import com.sojicute.cradle.domain.Road;
import com.sojicute.cradle.domain.User;
import com.sojicute.cradle.services.RoadServiceImpl;
import com.sojicute.cradle.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

//    @GetMapping("/user/{username}/")
//    public ResponseEntity<User> getUser(String username){
//
//    }
//
//    @GetMapping("/user/{username}/road/")
//    public ResponseEntity<List<Road>> getRoadsByUser(String username) {
//
//    }
//
//    @GetMapping("/user/{username}/road/{road_id}")
//    public ResponseEntity<Road> getRoadByUser(@PathVariable("username") String username, @PathVariable("road_id") long road_id) {
//        userService.findByUsername(username);
//    }

}