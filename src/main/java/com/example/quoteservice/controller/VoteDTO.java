package com.example.quoteservice.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class VoteDTO {
    @Min(1)
    private Long quoteId;
    @Min(-1)
    @Max(1)
    private byte opinion;
    @Min(1)
    private long voterId;
}
