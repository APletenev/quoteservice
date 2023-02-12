package com.example.quoteservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "quote")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String content;
    @PastOrPresent
    @NotNull
    private LocalDate creationDate;
    @PastOrPresent
    @NotNull
    private LocalDate updateDate;
    @Min(1)
    private long authorId;
    private long score;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quote")
    @OrderBy("votingTime")
    private Set<Vote> votes;
}
