package com.BookLoanding.book_loanding.book.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String author;

    @NotNull
    private Integer stock;

    @NotNull
    @Size(min = 1)
    private List<Short> categories;

    @NotBlank
    private String imageUrl;
}
