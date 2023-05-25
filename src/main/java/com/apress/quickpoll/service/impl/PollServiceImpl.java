package com.apress.quickpoll.service.impl;

import com.apress.quickpoll.entity.Poll;
import com.apress.quickpoll.exception.ResourceNotFoundException;
import com.apress.quickpoll.repository.PollRepository;
import com.apress.quickpoll.service.PollService;
import com.apress.quickpoll.vo.PollVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;

    @Override
    public List<PollVO> getPolls(Pageable pageable) {
        List<PollVO> pollVOs = new ArrayList<>();
        Iterable<Poll> polls = pollRepository.findAll(pageable);
        polls.forEach(poll -> pollVOs.add(new PollVO(poll.getId(), poll.getQuestion(), poll.getOptions())));
        return pollVOs;
    }

    @Override
    public PollVO createPoll(PollVO pollVO) {
        Poll poll = pollRepository.save(new Poll(pollVO.id(), pollVO.question(), pollVO.options()));
        return new PollVO(poll.getId(), poll.getQuestion(), poll.getOptions());
    }

    @Override
    public PollVO getPollById(Long pollId) {
        Optional<Poll> optionalPoll = pollRepository.findById(pollId);
        if(optionalPoll.isPresent()) {
            return new PollVO(optionalPoll.get().getId(), optionalPoll.get().getQuestion(), optionalPoll.get().getOptions());
        }
        throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
    }

    @Override
    public PollVO updatePoll(PollVO pollVO, Long pollId) {
        verifyPollById(pollId);
        Poll poll = pollRepository.save(new Poll(pollVO.id(), pollVO.question(), pollVO.options()));
        return new PollVO(poll.getId(), poll.getQuestion(), poll.getOptions());
    }

    @Override
    public void deletePoll(Long pollId) {
        verifyPollById(pollId);
        pollRepository.deleteById(pollId);
    }

    @Override
    public void verifyPollById(Long pollId) {
        Optional<Poll> optionalPoll = pollRepository.findById(pollId);
        if(optionalPoll.isEmpty()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }

}
