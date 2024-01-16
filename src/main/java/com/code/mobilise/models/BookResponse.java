package com.code.mobilise.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Builder
@Data
@Accessors(chain = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private Long id;

    private String title;

    private String author;

    private LocalDate publicationYear;
}
