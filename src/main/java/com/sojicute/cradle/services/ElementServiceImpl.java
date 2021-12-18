package com.sojicute.cradle.services;

import com.sojicute.cradle.api.exception.ResourceNotFoundException;
import com.sojicute.cradle.domain.Element;
import com.sojicute.cradle.repository.ElementRepository;
import com.sojicute.cradle.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementServiceImpl implements ElementService{

    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private RoadRepository roadRepository;

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


    /*



     */


    @Override
    public List<Element> findElementsByRoadId(Long id) {
        return elementRepository.findElementsByRoadId(id);
    }

    @Override
    public Element createElement(Long roadId, Element element) {
        return roadRepository.findById(roadId).map(road -> {
            element.setRoad(road);
            return elementRepository.save(element);
        }).orElseThrow(() -> new ResourceNotFoundException("Road with id "+ roadId + " not found"));
    }

    @Override
    public Element updateElement(Long roadId, Long elementId, Element element) {

        if(!elementRepository.existsById(roadId)) {
            throw new ResourceNotFoundException("Road with id "+ roadId + " not found");
        }

        return elementRepository.findById(elementId).map(elem -> {
            elem.setTitle(element.getTitle());
            elem.setText(element.getText());
            return elementRepository.save(elem);
        } ).orElseThrow(() -> new ResourceNotFoundException("Element with id "+ elementId + "not found"));
    }

    @Override
    public void deleteElement(Long roadId, Long elementId) {

        if(!elementRepository.existsById(roadId)) {
            throw new ResourceNotFoundException("Road with id "+ roadId + " not found");
        }

        elementRepository.findById(elementId).map(element -> {
            elementRepository.delete(element);
            return "Ok";
        }).orElseThrow(() -> new ResourceNotFoundException("Element with id "+ elementId + "not found or already deleted"));

    }


}
