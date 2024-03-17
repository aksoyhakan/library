package com.example.library.mapping;


import com.example.library.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class AuthorReturn {
    private String firstName;
    private String lastName;

    public AuthorReturn(Author author) {
        this.firstName = author.getFirstName();
        this.lastName=author.getLastName();
    }
}
