package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Element;

import java.util.List;

public interface ElementService {

    List<Element> findAll();

    Element findElementById(Long id);

    Element addNewElement(Element element);

    Element updateElementById(Long id, Element element);

    void deleteElementById(Long id);

    List<Element> findElementsByRoadId(Long id);

    Element createElement(Long roadId, Element element);

    Element updateElement(Long roadId, Long elementId, Element element);

    void deleteElement(Long roadId, Long elementId);
}
