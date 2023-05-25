package com.apress.quickpoll.service.impl;

import com.apress.quickpoll.entity.Option;
import com.apress.quickpoll.repository.VoteRepository;
import com.apress.quickpoll.service.ComputeResultService;
import com.apress.quickpoll.service.PollService;
import com.apress.quickpoll.vo.OptionCountVO;
import com.apress.quickpoll.vo.VoteResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ComputeResultServiceImpl implements ComputeResultService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PollService pollService;

    @Override
    public VoteResultVO computeResult(Long pollId) {
        pollService.verifyPollById(pollId);
        List<Option> options = new ArrayList<>();
        voteRepository.findByPoll(pollId).forEach(vote -> options.add(vote.getOption()));
        Map<Long, List<Option>> optionMap =  options.stream().collect(Collectors.groupingBy(Option :: getId));
        int totalVotes = 0;
        List<OptionCountVO> optionCounts = new ArrayList<>();
        for(Map.Entry<Long, List<Option>> entry : optionMap.entrySet()) {
            totalVotes += entry.getValue().size();
            optionCounts.add(new OptionCountVO(entry.getKey(), entry.getValue().size()));
        }
        return new VoteResultVO(totalVotes, optionCounts);
    }
}
