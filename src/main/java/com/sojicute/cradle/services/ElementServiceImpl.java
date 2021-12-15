package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Element;
import com.sojicute.cradle.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementServiceImpl implements ElementService{

    @Autowired
    private ElementRepository elementRepository;

    public ElementServiceImpl() {
    }

    @Override
    public List<Element> findAll() {
        return elementRepository.findAll();
    }

    @Override
    public Element findElementById(Long id) {
        return elementRepository.findById(id).get();
    }

    @Override
    public Element addNewElement(Element element) {
        return elementRepository.save(element);
    }

    @Override
    public Element updateElementById(Long id, Element element) {
        return elementRepository.save(element);
    }

    @Override
    public void deleteElementById(Long id) {
        elementRepository.deleteById(id);
    }

    @Override
    public List<Element> findElementsByRoadId(Long id) {
        return elementRepository.findElementsByRoadId(id);
    }

}
