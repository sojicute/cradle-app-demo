package com.sojicute.cradle.repository;

import com.sojicute.cradle.domain.Element;
import com.sojicute.cradle.domain.Road;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ElementRepositoryTest {

    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldSaveElement() {
        Element element = new Element("Spring", "Framework");
        Element expectedElement = elementRepository.save(element);

        assertThat(expectedElement).isNotNull();
    }

    @Test
    void shouldFindElementById() {
        Element expectedElement = new Element("Spring", "Framework");
        Long id = entityManager.persistAndGetId(expectedElement, Long.class);

        Optional<Element> element = elementRepository.findById(id);

        assertThat(element.get().getTitle()).isEqualTo(expectedElement.getTitle());
    }

    @Test
    void shouldFindAllElements() {
        Element expectedElement = new Element("Spring", "Framework");
        entityManager.persist(expectedElement);

        List<Element> elementList = elementRepository.findAll();

        assertThat(elementList).isNotNull();
    }

    @Test
    void shouldDeleteElements() {
        Element expectedElement = new Element("Spring", "Framework");
        entityManager.persist(expectedElement);

        elementRepository.delete(expectedElement);

        List<Element> elementList = elementRepository.findAll();

        assertThat(elementList).hasSize(0);
    }
}
