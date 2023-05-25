package com.apress.quickpoll.controller;

import com.apress.quickpoll.service.VoteService;
import com.apress.quickpoll.vo.ErrorDetailVO;
import com.apress.quickpoll.vo.VoteResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeResultController {

    @Autowired
    private VoteService voteService;

    @GetMapping(path = "/computeResult")
    @Operation(summary = "Computes the result of a given poll by id", description = "Poll result will be sent as a response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Poll result computation done successfully", content = @Content(schema = @Schema(implementation = VoteResultVO.class))),
            @ApiResponse(responseCode = "404", description = "Poll not found for the given id", content = @Content(schema = @Schema(implementation = ErrorDetailVO.class)))
    })
    public ResponseEntity<?> computeResult(@RequestParam(name = "pollId", required = true) Long pollId) {
        return new ResponseEntity<>(voteService.getVotesByPollId(pollId), HttpStatus.OK);
    }
}
