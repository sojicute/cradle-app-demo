package com.sojicute.cradle.api;

import com.sojicute.cradle.domain.User;
import com.sojicute.cradle.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username){
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
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