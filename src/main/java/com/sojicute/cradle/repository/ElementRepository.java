package com.sojicute.cradle.repository;

import com.sojicute.cradle.domain.Element;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ElementRepository extends JpaRepository<Element, Long> {
    List<Element> findElementsByRoadId(Long roadId);
}
