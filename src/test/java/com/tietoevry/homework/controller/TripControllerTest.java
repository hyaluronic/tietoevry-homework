package com.tietoevry.homework.controller;

import com.tietoevry.homework.service.ItemRetriever;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TripController.class)
public class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemRetriever itemRetriever;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTripItems() throws Exception {
        long kilometers = 100;
        LocalDate startDate = LocalDate.now();

        Map<String, Integer> items = Collections.singletonMap("Item 1", 1);
        when(itemRetriever.getItems(anyLong(), any(LocalDate.class))).thenReturn(items);

        mockMvc.perform(MockMvcRequestBuilders.get("/trip/items")
                        .param("kilometers", String.valueOf(kilometers))
                        .param("startDate", startDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.['Item 1']").value(1));
    }
}

