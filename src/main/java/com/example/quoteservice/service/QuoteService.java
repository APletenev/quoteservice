package com.example.quoteservice.service;

import com.example.quoteservice.entity.Quote;

import java.sql.SQLException;

public interface QuoteService {
    Quote saveQuote (Quote quote);
    Quote getQuoteById(Long userId);
    Quote getRandomQuote() throws SQLException;
    void deleteQuoteById(Long userId);

}
