package com.example.quoteservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
    private long score; // saves total voting score to avoid redundant map scanning
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quote")
    @OrderBy("votingDate")
    @JsonIgnore
    private Map<Long,Vote> votes = new HashMap<>();

    public boolean addVote(Vote vote) {
        if (votes.containsKey(vote.getVoterId())) return false;
        votes.put(vote.getVoterId(),vote);
        score+= vote.getOpinion();
        return true;
    }

}
