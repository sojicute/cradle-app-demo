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

    @BeforeEach
    void setUp() {
        Road road = new Road("Java", "Java Developer");

        Element element1 = new Element("Spring", "Spring");
        Element element2 = new Element("Kafka", "jms");

        road.getElements().add(element1);
        road.getElements().add(element2);
    }


    @Test
    void shouldFindById() {
        assertThat(roadRepository.count()).isEqualTo(1);
        assertThat(elementRepository.count()).isEqualTo(2);
    }

}
