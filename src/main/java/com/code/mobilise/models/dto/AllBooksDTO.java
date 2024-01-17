package com.code.mobilise.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllBooksDTO {

    private int size = 10; // Default size
    private int index = 0; // Default page index
}
