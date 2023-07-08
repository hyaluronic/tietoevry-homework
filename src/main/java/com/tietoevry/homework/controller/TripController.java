package com.tietoevry.homework.controller;

import com.tietoevry.homework.service.ItemRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final ItemRetriever itemRetriever;

    @GetMapping("/items")
    public ResponseEntity<Map<String, Integer>> getTripItems(
            @RequestParam("kilometers") long kilometers,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
    ) {
        try {
            return new ResponseEntity<>(
                    itemRetriever.getItems(kilometers, startDate),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
