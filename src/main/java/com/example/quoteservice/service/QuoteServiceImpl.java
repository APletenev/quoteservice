package com.example.quoteservice.service;

import com.example.quoteservice.entity.Quote;
import com.example.quoteservice.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private QuoteRepository quoteRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Quote saveQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public Quote getQuoteById(Long quoteId) {
        return quoteRepository.findById(quoteId).get();
    }

    @Override
    public Quote getRandomQuote() throws SQLException {
        long rndId;
        try (Connection dbConnection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("select * from quote");
            ResultSet resultSet = preparedStatement.executeQuery();
            long lastRowNumber = quoteRepository.count();
            //Calculate random number between 1 and last row number
            double f = Math.random()/Math.nextDown(1.0);
            int rndRowNumber = (int) Math.round ((1.0 - f) + lastRowNumber*f);

            resultSet.absolute(rndRowNumber);
            rndId = resultSet.getLong("id");
        }
        return quoteRepository.findById(rndId).get();
    }

    @Override
    public void deleteQuoteById(Long quoteId) {
        quoteRepository.deleteById(quoteId);
    }

    @Override
    public List<Quote> getTop10Quotes() {
        return quoteRepository.findTop10ByOrderByScoreDesc();
    }

    @Override
    public List<Quote> getWorse10Quotes() {
        return quoteRepository.findFirst10ByOrderByScoreAsc();
    }

}
