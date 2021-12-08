package com.sojicute.cradle.repository;

import com.sojicute.cradle.domain.Element;
import com.sojicute.cradle.domain.Road;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RoadRepositoryTest {

    @Autowired
    private RoadRepository roadRepository;

    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldSaveRoadWithElements() {
        Road road = new Road("Java", "Java Developer");

        Element element_1 = Element.builder()
                .title("Spring")
                .text("framework")
                .build();

        Element element_2 = Element.builder()
                .title("Docker")
                .text("tool")
                .build();

        road.getElements().add(element_1);
        road.getElements().add(element_2);

        roadRepository.save(road);

        assertThat(roadRepository.count()).isEqualTo(1);
        assertThat(elementRepository.count()).isEqualTo(2);
    }

    @Test
    void shouldSaveRoad() {
        Road road = new Road("Golang", "Golang Developer");
        Road expectedRoad = roadRepository.save(road);

        Road actualRoad = roadRepository.getById(expectedRoad.getId());

        assertThat(expectedRoad).isEqualTo(actualRoad);
    }

    @Test
    void shouldFindRoadById() {
        Road expectedRoad = new Road("Golang", "Golang Developer");
        Long id = entityManager.persistAndGetId(expectedRoad, Long.class);

        Road actualRoad = roadRepository.findById(id).get();

        assertThat(expectedRoad).isEqualTo(actualRoad);

    }

    @Test
    void shouldFindAllRoads() {
        Road expectedRoad = new Road("Golang", "Golang Developer");
        entityManager.persist(expectedRoad);

        List<Road> roads = roadRepository.findAll();
        assertThat(roads).hasSize(1);
    }


    @Test
    void shouldDeleteRoad() {
        Road expectedRoad = new Road("Golang", "Golang Developer");
        Road road = entityManager.persist(expectedRoad);
//        entityManager.flush();
        roadRepository.delete(road);
        assertThat(roadRepository.count()).isEqualTo(0);
    }

}
