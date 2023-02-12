package com.example.quoteservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "vote")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Quote quote;
    @Min(-1)
    @Max(1)
    @NotEmpty
    private byte opinion;
    @Min(1)
    private long voterId;
    @PastOrPresent
    @NotNull
    private LocalDateTime votingTime;
}
