package com.sojicute.cradle.repository;

import com.sojicute.cradle.domain.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ElementRepositoryTest {

    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private TestEntityManager entityManager;

    Element ELEMENT_1 = Element.builder()
            .title("Spring")
            .text("framework")
            .build();

    @Test
    void shouldSaveElement() {
        Element expectedElement = elementRepository.save(ELEMENT_1);

        assertThat(expectedElement).isNotNull();
    }

    @Test
    void shouldFindElementById() {
        Long id = entityManager.persistAndGetId(ELEMENT_1, Long.class);

        Optional<Element> element = elementRepository.findById(id);

        assertThat(element.get().getTitle()).isEqualTo(ELEMENT_1.getTitle());
    }

    @Test
    void shouldFindAllElements() {
        entityManager.persist(ELEMENT_1);

        List<Element> elementList = elementRepository.findAll();

        assertThat(elementList).isNotNull();
    }

    @Test
    void shouldDeleteElements() {
        entityManager.persist(ELEMENT_1);

        elementRepository.delete(ELEMENT_1);

        List<Element> elementList = elementRepository.findAll();

        assertThat(elementList).hasSize(0);
    }
}
