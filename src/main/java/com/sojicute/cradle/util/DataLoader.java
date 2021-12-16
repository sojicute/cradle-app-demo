package com.sojicute.cradle.util;

import com.sojicute.cradle.domain.Road;
import com.sojicute.cradle.domain.Role;
import com.sojicute.cradle.domain.User;
import com.sojicute.cradle.repository.ElementRepository;
import com.sojicute.cradle.repository.RoadRepository;
import com.sojicute.cradle.repository.RoleRepository;
import com.sojicute.cradle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataLoader {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoadRepository roadRepository;

    @Autowired
    private ElementRepository elementRepository;


    private static final Long ADMIN_ID = 1L;
    private static final String ADMIN_NAME = "admin";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    private static final Long EDITOR_ID = 2L;
    private static final String EDITOR_NAME = "editor";
    private static final String EDITOR_ROLE = "ROLE_EDITOR";


    private static final Long USER_ID = 3L;
    private static final String USERNAME = "sojicute";
    private static final String USER_ROLE = "ROLE_USER";

    @PostConstruct
    public void loadData() {
        Role role = Role.builder().name(USER_ROLE).build();

        Role savedRole = roleRepository.save(role);


        Road road = Road.builder().title("New").build();

        User user = User.builder().username(USERNAME).password("password").roads(List.of(road)).roles(List.of(role)).build();
//
        User sojicute = userRepository.save(user);
//
//        role.setUsers(List.of(sojicute));
//
//        roleRepository.save(role);


//        Role roleEditor = Role.builder().name(EDITOR_ROLE).build();
//        User editor = User.builder().username(EDITOR_NAME).roles(Set.of(roleEditor)).build();
//
//        Role roleAdmin = Role.builder().name(ADMIN_ROLE).build();
//        User admin = User.builder().username(ADMIN_NAME).roles(Set.of(roleAdmin)).build();

//        userRepository.save(user);
////        userRepository.save(editor);
////        userRepository.save(admin);
//
//        Road road = Road.builder()
//                .title("Java")
//                .description("Java developer")
//                .build();
//
//        user.setRoads(Arrays.asList(road));

//        userRepository.save(user);





    }

//    @PreDestroy
//    public void removeData() {
//        userRepository.deleteAll();
//    }
}
