package com.apress.quickpoll.service.impl;

import com.apress.quickpoll.entity.Option;
import com.apress.quickpoll.entity.Vote;
import com.apress.quickpoll.repository.VoteRepository;
import com.apress.quickpoll.service.PollService;
import com.apress.quickpoll.service.VoteService;
import com.apress.quickpoll.vo.OptionVO;
import com.apress.quickpoll.vo.VoteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private PollService pollService;

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public VoteVO createVote(VoteVO voteVO, Long pollId) {
        pollService.verifyPollById(pollId);
        Vote vote = voteRepository.save(new Vote(voteVO.id(), new Option(voteVO.optionVO().id(), voteVO.optionVO().value())));
        return new VoteVO(vote.getId(), new OptionVO(vote.getId(), vote.getOption().getValue()));
    }

    @Override
    public List<VoteVO> getVotesByPollId(Long pollId) {
        List<VoteVO> voteVOs = new ArrayList<>();
        pollService.verifyPollById(pollId);
        voteRepository.findByPoll(pollId).forEach(vote -> voteVOs.add(new VoteVO(vote.getId(), new OptionVO(vote.getId(), vote.getOption().getValue()))));
        return voteVOs;
    }
}
