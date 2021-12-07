package com.sojicute.cradle.api;

import com.sojicute.cradle.domain.Element;
import com.sojicute.cradle.services.ElementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ElementController {

    @Autowired
    private ElementServiceImpl elementService;

    public ElementController() {
    }

    @GetMapping("/element")
    public ResponseEntity<List<Element>> getAllElements() {
        List<Element> elements = elementService.findAll();
        return elements != null && !elements.isEmpty()
                ? new ResponseEntity<>(elements, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/element/{id}")
    public ResponseEntity<Element> getElement(@PathVariable("id") long id) {
        Element element = elementService.findElementById(id);
        return element != null
            ? new ResponseEntity<>(element, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/element")
    public ResponseEntity<Element> saveElement(@RequestBody Element element) {
        elementService.addNewElement(element);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/element/{id}")
    public ResponseEntity<Element> updateElement(@PathVariable long id, @RequestBody Element element) {
        elementService.updateElementById(id, element);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/element/{id}")
    public ResponseEntity<Void> deleteElement(@PathVariable("id") long id) {
        elementService.deleteElementById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
