package com.apress.quickpoll.service;

import com.apress.quickpoll.vo.VoteResultVO;
import org.springframework.stereotype.Service;

@Service
public interface ComputeResultService {

    VoteResultVO computeResult(Long pollId);
}
