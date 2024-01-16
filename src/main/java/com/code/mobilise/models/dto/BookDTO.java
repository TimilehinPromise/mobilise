package com.code.mobilise.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long bookId;

    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title must not exceed 50 characters")
    private String title;

    @NotBlank(message = "Title must not be blank")
    @Size(max = 50, message = "Author name must not exceed 50 characters")
    private String author;

    @PastOrPresent(message = "Publication year must be in the past or present")
    private LocalDate publicationYear;
}
