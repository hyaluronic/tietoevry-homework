package com.tietoevry.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class TripDTO {

    @NotEmpty
    private long kilometers;
    @NotEmpty
    private LocalDate startDate;
}
