package com.tietoevry.homework.controller;

import com.tietoevry.homework.dto.TripDTO;
import com.tietoevry.homework.service.ItemRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TripController {

    @Autowired
    private ItemRetriever itemRetriever;

    @PostMapping("/trip")
    public ResponseEntity<Map<String, Integer>> getTripItems(@RequestBody @Valid TripDTO tripDTO) {
        try {
            return new ResponseEntity<>(
                    itemRetriever.getItems(tripDTO.getKilometers(), tripDTO.getStartDate()),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
