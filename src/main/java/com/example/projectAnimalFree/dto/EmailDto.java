package com.example.projectAnimalFree.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EmailDto {

    private Long id;

    private String email;

    private String text;

    private String localDate;

    public EmailDto() {

    }
}
