package com.example.quoteservice.service;

import com.example.quoteservice.entity.Quote;

import java.sql.SQLException;
import java.util.List;

public interface QuoteService {
    Quote saveQuote(Quote quote);

    Quote getQuoteById(Long userId);

    Quote getRandomQuote() throws SQLException;

    void deleteQuoteById(Long userId);

    List<Quote> getTop10Quotes();

    List<Quote> getWorse10Quotes();
}
