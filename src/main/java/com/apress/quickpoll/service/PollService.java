package com.apress.quickpoll.service;

import com.apress.quickpoll.vo.PollVO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PollService {

    List<PollVO> getPolls(Pageable pageable);

    PollVO createPoll(PollVO pollVO);

    PollVO getPollById(Long pollId);

    PollVO updatePoll(PollVO pollVO, Long pollId);

    void deletePoll(Long pollId);

    void verifyPollById(Long pollId);
}
