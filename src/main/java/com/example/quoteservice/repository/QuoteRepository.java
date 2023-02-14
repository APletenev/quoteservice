package com.example.quoteservice.repository;

import com.example.quoteservice.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findTop10ByOrderByScoreDesc();
    List<Quote> findFirst10ByOrderByScoreAsc();

}
