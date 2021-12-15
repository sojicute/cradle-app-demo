package com.sojicute.cradle.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sojicute.cradle.domain.Road;
import com.sojicute.cradle.services.RoadServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "user")
@WebMvcTest(RoadController.class)
class RoadControllerTest {

    @MockBean
    private RoadServiceImpl roadService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private final String ID = "/1";
    private final String URL = "/api/road";

    Road ROAD_1 = Road.builder()
            .id(1L)
            .title("Java")
            .description("Developer")
            .build();

    Road ROAD_2 = Road.builder()
            .id(2L)
            .title("Golang")
            .description("Developer")
            .build();


    @Test
    void shouldGetAllRoads() throws Exception {
        List<Road> roads = new ArrayList<>(Arrays.asList(ROAD_1, ROAD_2));

        Mockito.when(roadService.findAll()).thenReturn(roads);

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetRoadByID() throws Exception {
        Mockito.when(roadService.findRoadById(ROAD_1.getId())).thenReturn(ROAD_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL+ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldPostRoad() throws Exception {
        Mockito.when(roadService.addNewRoad(ROAD_1)).thenReturn(ROAD_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(this.mapper.writeValueAsString(ROAD_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateRoad() throws Exception{
        Mockito.when(roadService.findRoadById(ROAD_1.getId())).thenReturn(ROAD_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put(URL+ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(this.mapper.writeValueAsString(ROAD_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteRoad() throws Exception {
        Mockito.when(roadService.findRoadById(ROAD_1.getId())).thenReturn(ROAD_1);

        mockMvc.perform(MockMvcRequestBuilders
                .delete(URL+ID)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}