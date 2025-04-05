package com.BookLoanding.book_loanding.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO  implements Serializable {

    private Short id;
    private String name;
    private String description;

}
