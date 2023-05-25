package com.apress.quickpoll.service;

import com.apress.quickpoll.vo.VoteVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteService {

    VoteVO createVote(VoteVO voteVO, Long pollId);

    List<VoteVO> getVotesByPollId(Long pollId);
}
