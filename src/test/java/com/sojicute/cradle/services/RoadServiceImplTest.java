package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Road;
import com.sojicute.cradle.domain.Role;
import com.sojicute.cradle.domain.User;
import com.sojicute.cradle.repository.RoadRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@EnableGlobalMethodSecurity(prePostEnabled = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoadServiceImplTest {

    @Autowired
    private RoadServiceImpl roadService;

    @MockBean
    private RoadRepository roadRepository;

    private static final Long ADMIN_ID = 1L;
    private static final String ADMIN_NAME = "admin";
    private static final String ADMIN_ROLE = "ADMIN";

    private static final Long EDITOR_ID = 2L;
    private static final String EDITOR_NAME = "editor";
    private static final String EDITOR_ROLE = "EDITOR";


    private static final Long USER_ID = 3L;
    private static final String USERNAME = "sojicute";
    private static final String USER_ROLE = "USER";

    private Road ROAD;

    @BeforeAll
    public void SetUp() {
        Role roleUser = Role.builder().name(USER_ROLE).build();
        User user = User.builder().id(USER_ID).username(USERNAME).password("{noop}password").roles(List.of(roleUser)).build();

        Role roleEditor = Role.builder().name(EDITOR_ROLE).build();
        User editor = User.builder().id(EDITOR_ID).username(EDITOR_NAME).roles(List.of(roleEditor)).build();

        Role roleAdmin = Role.builder().name(ADMIN_ROLE).build();
        User admin = User.builder().id(ADMIN_ID).username(ADMIN_NAME).roles(List.of(roleAdmin)).build();

        this.ROAD = Road.builder()
                .id(1L)
                .title("Java")
                .description("Java developer")
                .user(user)
                .build();
    }

    @Test
    @WithMockUser(username = USERNAME, roles = {USER_ROLE})
    void shouldFindAllRoad() {
        List<Road> roadList = Arrays.asList(ROAD);
        Mockito.when(roadRepository.findAll()).thenReturn(roadList);

        List<Road> expectedRoadList = roadService.findAll();

        assertThat(roadList).isNotEmpty();
        assertThat(roadList).isEqualTo(expectedRoadList);
    }

    @Test
    @WithAnonymousUser
    void shouldFindAllRoad_AnonymousUser_AccessIsDenied() {
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            roadService.findAll();
        });
    }

    @Test
    @WithMockUser(username = USERNAME, roles = {USER_ROLE})
    void shouldFindRoadById() {
        Mockito.when(roadRepository.findById(1L)).thenReturn(Optional.of(ROAD));

        Road expectedRoad = roadService.findRoadById(1L);

        assertThat(ROAD).isNotNull();
        assertThat(ROAD).isEqualTo(expectedRoad);
    }

    @Test
    @WithAnonymousUser
    void shouldFindRoadById_AnonymousUser_AccessIsDenied() {
        Mockito.when(roadRepository.findById(1L)).thenReturn(Optional.of(ROAD));

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            roadService.findRoadById(1L);
        });
    }

    @Test
    @WithMockUser(username = USERNAME, roles = {USER_ROLE})
    void shouldAddNewRoad_IsOwner_Success() {
        Mockito.when(roadRepository.save(ROAD)).thenReturn(ROAD);

        Road expectedRoad = roadService.addNewRoad(ROAD);

        assertThat(ROAD).isEqualTo(expectedRoad);
    }

    @Test
    @WithMockUser(username = ADMIN_NAME, roles = {ADMIN_ROLE})
    void shouldAddNewRoad_AdminRole_Success() {
        Mockito.when(roadRepository.save(ROAD)).thenReturn(ROAD);

        Road expectedRoad = roadService.addNewRoad(ROAD);

        assertThat(ROAD).isEqualTo(expectedRoad);
    }

    @Test
    @WithAnonymousUser
    void shouldAddNewRoad_AnonymousUser_AccessIsDenied() {
        Mockito.when(roadRepository.save(ROAD)).thenReturn(ROAD);

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            roadService.addNewRoad(ROAD);
        });
    }

    @Test
    @WithMockUser(username = EDITOR_NAME, roles = {USER_ROLE})
    void shouldAddNewRoad_IsNotOwner_AccessIsDenied() {
        Mockito.when(roadRepository.save(ROAD)).thenReturn(ROAD);

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            roadService.addNewRoad(ROAD);
        });
    }

    @Test
    @WithMockUser(username = USERNAME, roles = {USER_ROLE})
    void shouldUpdateRoadById_IsOwner_Success() {
        Mockito.when(roadRepository.findById(ROAD.getId())).thenReturn(Optional.ofNullable(ROAD));

        roadService.updateRoadById(ROAD.getId(), ROAD);

    }

    @Test
    @WithMockUser(username = ADMIN_NAME, roles = {ADMIN_ROLE})
    void shouldUpdateRoadById_IsAdmin_Success() {
        Mockito.when(roadRepository.findById(ROAD.getId())).thenReturn(Optional.ofNullable(ROAD));

        roadService.updateRoadById(ROAD.getId(), ROAD);

    }


    @Test
    @WithMockUser(username = EDITOR_NAME, roles = {USER_ROLE})
    void shouldUpdateRoadById_IsNotOwner_AccessIsDenied() {
        Mockito.when(roadRepository.findById(ROAD.getId())).thenReturn(Optional.ofNullable(ROAD));

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            roadService.updateRoadById(ROAD.getId(), ROAD);
        });
    }

    @Test
    @WithAnonymousUser
    void shouldUpdateRoadById_AnonymousUser_AccessIsDenied() {
        Mockito.when(roadRepository.findById(ROAD.getId())).thenReturn(Optional.ofNullable(ROAD));

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            roadService.updateRoadById(ROAD.getId(), ROAD);
        });
    }


    @Test
    @WithMockUser(username = ADMIN_NAME, roles = {ADMIN_ROLE})
    void shouldDeleteRoadById_IsAdmin_Success() {
        Mockito.when(roadRepository.findById(ROAD.getId())).thenReturn(Optional.ofNullable(ROAD));

        roadService.deleteRoadById(ROAD.getId());
    }

    @Test
    @WithAnonymousUser
    void shouldDeleteRoadById_AnonymousUser_AccessIsDenied() {
        Mockito.when(roadRepository.findById(ROAD.getId())).thenReturn(Optional.ofNullable(ROAD));

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            roadService.updateRoadById(ROAD.getId(), ROAD);
        });
    }
}