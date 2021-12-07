package com.sojicute.cradle.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.TestUtils;
import com.sojicute.cradle.domain.Element;
import com.sojicute.cradle.services.ElementService;
import com.sojicute.cradle.services.ElementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ElementController.class)
class ElementControllerTest {

    @MockBean
    private ElementServiceImpl elementService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    Element ELEMENT1 = new Element("Spring", "framework");
    Element ELEMENT2 = new Element("Gradle", "repository");

    @WithMockUser(username = "admin")
    @Test
    void shouldReturnALlElements() throws Exception {
        List<Element> elements = new ArrayList<>(Arrays.asList(ELEMENT1, ELEMENT2));

        Mockito.when(elementService.findAll()).thenReturn(elements);


        mockMvc.perform(get("/api/element")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin")
    void shouldPostElement() throws Exception {
        Element element = new Element("Spring", "framework");
        element.setId(1L);

        Mockito.when(elementService.addNewElement(element)).thenReturn(element);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/element")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(this.mapper.writeValueAsString(element));


        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }


    @WithMockUser(username = "admin")
    @Test
    void shouldGetElementById() throws Exception {
        Element element = new Element("Spring", "framework");
        element.setId(1L);

        Mockito.when(elementService.findElementById(element.getId())).thenReturn(element);

        mockMvc.perform(get("/api/element/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "admin")
    @Test
    void shouldUpdateElement() throws Exception{
        Element updatedElement = new Element("Spring", "framework");
        updatedElement.setId(1L);

        Mockito.when(elementService.updateElementById(updatedElement.getId(), updatedElement)).thenReturn(updatedElement);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/element/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(this.mapper.writeValueAsString(updatedElement));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
}