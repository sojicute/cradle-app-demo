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

    private final String ID = "/1";
    private final String URL = "/api/element";

    Element ELEMENT_1 = Element.builder()
            .id(1L)
            .title("Spring")
            .text("framework")
            .build();

    Element ELEMENT_2 = Element.builder()
            .id(2L)
            .title("Docker")
            .text("tool")
            .build();

    @WithMockUser(username = "admin")
    @Test
    void shouldReturnALlElements() throws Exception {
        List<Element> elements = new ArrayList<>(Arrays.asList(ELEMENT_1, ELEMENT_2));

        Mockito.when(elementService.findAll()).thenReturn(elements);

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin")
    void shouldPostElement() throws Exception {
        Mockito.when(elementService.addNewElement(ELEMENT_1)).thenReturn(ELEMENT_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(this.mapper.writeValueAsString(ELEMENT_1));


        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }


    @WithMockUser(username = "admin")
    @Test
    void shouldGetElementById() throws Exception {
        Mockito.when(elementService.findElementById(ELEMENT_1.getId())).thenReturn(ELEMENT_1);

        mockMvc.perform(get(URL+ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "admin")
    @Test
    void shouldUpdateElement() throws Exception {
        Mockito.when(elementService.updateElementById(ELEMENT_1.getId(), ELEMENT_1)).thenReturn(ELEMENT_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put(URL+ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(this.mapper.writeValueAsString(ELEMENT_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "admin")
    @Test
    void shouldDeleteElement() throws Exception {
        Mockito.when(elementService.findElementById(ELEMENT_1.getId())).thenReturn(ELEMENT_1);

        mockMvc.perform(MockMvcRequestBuilders
                .delete(URL+ID)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}