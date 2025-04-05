package com.practice.gtm.spring_boot_kafka_producer_demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.gtm.spring_boot_kafka_producer_demo.service.LocationService;
import org.assertj.core.api.BDDAssumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UpdateLocationController.class) // Specify the controller to test
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UpdateLocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LocationService locationService;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private UpdateLocationController updateLocationController;

    @Test
    public void UpdateController_updateLocation_ReturnCreated() throws Exception {
        Mockito.doNothing().when(locationService).updateLocation(ArgumentMatchers.anyString());
        ResultActions response = mockMvc.perform(put("/location").contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void UpdateController_updateLocation_ThrowsException() throws Exception {
        Mockito.doThrow(new InterruptedException("Test interruption")).when(locationService).updateLocation(ArgumentMatchers.anyString());
        ResultActions response = mockMvc.perform(put("/location").contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
}
