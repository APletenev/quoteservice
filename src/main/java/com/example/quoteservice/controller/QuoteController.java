package com.example.quoteservice.controller;

import com.example.quoteservice.client.UserClient;
import com.example.quoteservice.entity.Quote;
import com.example.quoteservice.entity.Vote;
import com.example.quoteservice.service.QuoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/quote")
@AllArgsConstructor
public class QuoteController {
    private QuoteService quoteService;
    private final UserClient userClient;

    /**
     * Creates or modifies of a quote.
     * @param quote Quote. In case quote id is not provided or null - creates a new quote.
     *              If existing quote id provided - replaces quote properties with provided ones.
     * @return persisted Quote in case of success, error messages otherwise
     */
    @PostMapping
    public Quote create(@RequestBody @Valid Quote quote) {
        // Check whether quote author exist in users service
        if (userClient.getUserById(quote.getAuthorId()).isEmpty())
            throw new IllegalArgumentException("Invalid author id");
        quote.setUpdateDate(LocalDate.now());
        return quoteService.saveQuote(quote);
    }

    /**
     * Get quote by user id
     * @param id - Quote id
     * @return Details of quote as JSON
     */
    @GetMapping("/{id}")
    public Quote byQuoteId(@PathVariable Long id) {
        return quoteService.getQuoteById(id);
    }

    /**
     *  Get a random quote
     * @return random quote's properties as JSON
     * @throws SQLException if a database access error occur
     */
    @GetMapping("/rnd")
    public Quote randomQuote() throws SQLException {
        return quoteService.getRandomQuote();
    }

    /**
     * Deletes quote by quote id
     * @param id quote id to delete
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        quoteService.deleteQuoteById(id);
    }

    /**
     * Top 10 quotes
     * @return List of 10 most upvoted quotes. JSON sorted from most voted to less.
     */
    @GetMapping("/top10")
    public List<Quote> top10Quotes() {
        return quoteService.getTop10Quotes();
    }

    /**
     * Worse 10 quotes
     * @return List of 10 quotes with less votes. JSON sorted from less voted to most.
     */
    @GetMapping("/worse10")
    public List<Quote> worse10Quotes() { return quoteService.getWorse10Quotes(); }

    /**
     * Evolution of the votes over time
     * @param quoteId id of quote to analyze
     * @return JSON list for score chart.
     * For every date when this quote was voted - pair "Date : total score of votes for this date"
     * Total score is sum all votes of all users voted for this quote for this date.
     * List has been sorted from early date to last.
     */
    @GetMapping("/votesintime/{quoteId}")
    public Map<LocalDate, Long> votesInTime(@PathVariable Long quoteId) {
        Quote quote = quoteService.getQuoteById(quoteId);
        Map<Long, Vote> votes = quote.getVotes();
        return new TreeMap<>(votes.values().stream()
                .collect(Collectors.groupingBy(Vote::getVotingDate, Collectors.summingLong(Vote::getOpinion))));
    }
}
