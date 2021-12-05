package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Element;

import java.util.List;

public interface ElementService {

    List<Element> findAll();

    Element findElementById(Long id);

    Element addNewElement(Element element);

    Element updateElementById(Long id, Element element);

    void deleteElementById(Long id);

}
