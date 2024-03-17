package com.example.library.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReturn {
    private int id;
    private String name;
    private AuthorReturn author;
    private CategoryReturn category;

}
