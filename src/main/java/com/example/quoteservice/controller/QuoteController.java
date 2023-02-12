package com.example.quoteservice.controller;

import com.example.quoteservice.client.UserClient;
import com.example.quoteservice.entity.Quote;
import com.example.quoteservice.service.QuoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;

@RestController
@RequestMapping("api/quote")
@AllArgsConstructor
public class QuoteController {
    private QuoteService quoteService;
    private final UserClient userClient;

    @PostMapping
    public Quote create(@RequestBody @Valid Quote quote) {
        // Check whether quote author exist in users servive
        if (userClient.getUserById(quote.getAuthorId()).isEmpty()) throw new IllegalArgumentException("Invalid author id");
        quote.setUpdateDate(LocalDate.now());
        return quoteService.saveQuote(quote);
    }
    @GetMapping("/{id}")
    public Quote byQuoteId(@PathVariable Long id) {
        return quoteService.getQuoteById(id);
    }

    @GetMapping("/rnd")
    public Quote randomQuote() throws SQLException {
        return quoteService.getRandomQuote();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        quoteService.deleteQuoteById(id);
    }

}
