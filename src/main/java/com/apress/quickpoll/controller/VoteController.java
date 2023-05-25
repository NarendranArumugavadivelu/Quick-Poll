package com.apress.quickpoll.controller;

import com.apress.quickpoll.entity.Vote;
import com.apress.quickpoll.repository.PollRepository;
import com.apress.quickpoll.repository.VoteRepository;
import com.apress.quickpoll.service.VoteService;
import com.apress.quickpoll.vo.ErrorDetailVO;
import com.apress.quickpoll.vo.VoteVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping(path = "/polls/{pollId}/votes")
    @Operation(summary = "Creates a vote", description = "Creates a vote and will be sent in the response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vote created successfully", content = @Content(schema = @Schema(implementation = Vote.class))),
            @ApiResponse(responseCode = "500", description = "Vote not created", content = @Content(schema = @Schema(implementation = ErrorDetailVO.class))),
            @ApiResponse(responseCode = "404", description = "Poll not found for the given id", content = @Content(schema = @Schema(implementation = ErrorDetailVO.class)))
    })
    public ResponseEntity<VoteVO> createVote(@PathVariable(name = "pollId", required = true) Long pollId, @RequestBody VoteVO voteVO) {
        voteVO = voteService.createVote(voteVO, pollId);
        HttpHeaders httpHeaders = new HttpHeaders();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(voteVO.id()).toUri();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(voteVO, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping(path = "/polls/{pollId}/votes")
    @Operation(summary = "Fetched all votes for a given poll id", description = "Fetched votes for a given poll id will be sent in the response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Votes for a given poll id fetched successfully", content = @Content(schema = @Schema(implementation = Vote.class))),
            @ApiResponse(responseCode = "404", description = "Poll not found for the given id", content = @Content(schema = @Schema(implementation = ErrorDetailVO.class)))
    })
    public ResponseEntity<List<VoteVO>> getVotes(@PathVariable(name = "pollId", required = true) Long pollId) {
        return new ResponseEntity<>(voteService.getVotesByPollId(pollId), HttpStatus.OK);
    }
}
