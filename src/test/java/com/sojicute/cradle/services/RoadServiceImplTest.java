package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Road;
import com.sojicute.cradle.domain.Role;
import com.sojicute.cradle.domain.User;

import com.sojicute.cradle.repository.RoadRepository;
import com.sojicute.cradle.repository.UserRepository;
import com.sojicute.cradle.security.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@EnableGlobalMethodSecurity(prePostEnabled = true)
class RoadServiceImplTest {

    @Autowired
    private RoadServiceImpl roadService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private RoadRepository roadRepository;

    private Road ROAD = Road.builder()
            .id(1L)
            .title("Java")
            .description("Java developer")
            .build();


    @Test
    void findAll() {
    }

    @Test
    void shouldFindRoadById_Success() {

        Mockito.when(roadRepository.findById(1L)).thenReturn(Optional.of(ROAD));

        Road expectedRoad = roadService.findRoadById(1L);

        assertThat(expectedRoad).isEqualTo(ROAD);
    }

    @Test
    @WithMockUser(username = "sojicute", roles={"USER"})
    void shouldAddNewRoad() {

        Role role = new Role();
        role.setName("USER");

        User user = User.builder()
                .id(3L)
                .username("sojicute")
                .password("{noop}password")
                .role(role)
                .build();

        Road road = Road.builder()
                .id(1L)
                .title("Java")
                .description("Java developer")
                .user(user)
                .build();


        Mockito.when(roadRepository.save(road)).thenReturn(road);

        Road expectedRoad = roadService.addNewRoad(road);

        assertThat(expectedRoad).isEqualTo(road);
    }

    @Test
    void updateRoadById() {
    }

    @Test
    void deleteRoadById() {
    }
}