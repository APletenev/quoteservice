package com.example.quoteservice.service;

import com.example.quoteservice.entity.Vote;
import com.example.quoteservice.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;
    @Override
    public Vote saveVote(Vote vote) {
        return voteRepository.save(vote);
    }
}
