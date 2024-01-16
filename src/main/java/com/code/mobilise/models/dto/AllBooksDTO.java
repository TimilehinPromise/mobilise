package com.code.mobilise.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllBooksDTO {

    @Min(value = 0 ,message = "size cannot be less than zero")
    private int size ;
    @Min(value = 0 ,message = "page index cannot be less than zero")
    private int index;
}
