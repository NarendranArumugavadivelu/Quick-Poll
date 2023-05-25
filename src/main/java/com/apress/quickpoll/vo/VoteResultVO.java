package com.apress.quickpoll.vo;

import java.util.List;

public record VoteResultVO(int totalVotes, List<OptionCountVO> optionCounts) {

}
