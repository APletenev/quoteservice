package com.example.quoteservice.controller;

import com.example.quoteservice.client.UserClient;
import com.example.quoteservice.entity.Quote;
import com.example.quoteservice.entity.Vote;
import com.example.quoteservice.service.QuoteService;
import com.example.quoteservice.service.VoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/vote")
@AllArgsConstructor
public class VoteController {
    private VoteService voteService;
    private QuoteService quoteService;
    private final UserClient userClient;

    @PostMapping
    @Transactional
    public Vote vote(@RequestBody @Valid VoteDTO voteDTO) {
        if (voteDTO.getOpinion()==0) throw new IllegalArgumentException("Opinion must not be zero");
        // Check whether vote author exist in users service
        if (userClient.getUserById(voteDTO.getVoterId()).isEmpty()) throw new IllegalArgumentException("Invalid voter id");
        //Throw exception in case of invalid quoteId
        Quote quote = quoteService.getQuoteById(voteDTO.getQuoteId());
        Vote vote = new Vote(null, quote, voteDTO.getOpinion(),voteDTO.getVoterId(),LocalDate.now());
        if (!quote.addVote(vote)) throw new IllegalArgumentException("This voter already voted for this quote");
        return voteService.saveVote(vote);
    }

}
