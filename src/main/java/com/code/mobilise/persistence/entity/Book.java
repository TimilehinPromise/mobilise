package com.code.mobilise.persistence.entity;


import com.code.mobilise.models.BookResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book extends BasePersistentEntity implements ToModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private LocalDate publicationYear;

    @Override
    public BookResponse toModel() {
        return BookResponse.builder()
                .id(id)
                .author(author)
                .title(title)
                .publicationYear(publicationYear)
                .build();
    }
}
