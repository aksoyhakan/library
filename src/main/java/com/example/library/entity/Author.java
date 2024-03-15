package com.example.library.entity;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "author",schema = "spring")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @Nonnull
    private String firstName;

    @Column(name="last_name")
    @Nonnull
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "author")
    private List<Book> book;
}
