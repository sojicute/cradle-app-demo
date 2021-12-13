package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Road;
import com.sojicute.cradle.repository.RoadRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

@SpringBootTest
class RoadServiceImplTest {

    @Autowired
    private RoadServiceImpl roadService;

    @MockBean
    private RoadRepository roadRepository;

    @Test
    void findAll() {
    }

    @Test
    void shouldFindRoadById() {
        Road road = new Road();
        Mockito.when(roadRepository.findById(1L)).thenReturn(Optional.of(road));

        Road exceptRoad = roadService.findRoadById(1L);

        assertThat(exceptRoad).isNotNull();
    }

    @Test
    void addNewRoad() {

    }

    @Test
    void updateRoadById() {
    }

    @Test
    void deleteRoadById() {
    }
}