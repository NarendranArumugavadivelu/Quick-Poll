package com.apress.quickpoll.controller;

import com.apress.quickpoll.entity.Poll;
import com.apress.quickpoll.service.PollService;
import com.apress.quickpoll.vo.ErrorDetailVO;
import com.apress.quickpoll.vo.PollVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import org.springframework.data.domain.Pageable;

@RestController
@Tag(name = "Poll", description = "Poll API")
public class PollController {

    @Autowired
    private PollService pollService;

    @GetMapping(path = "/polls")
    @Operation(summary = "Fetches all the available poll", description = "Fetched polls will be sent in the response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Polls fetched successfully", content = @Content(schema = @Schema(implementation = Iterable.class)))
    })
    public ResponseEntity<List<PollVO>> getPolls(@PageableDefault(size = 5) Pageable pageable) {
       return new ResponseEntity<>(pollService.getPolls(pageable), HttpStatus.OK);
    }

    @PostMapping(path = "/polls")
    @Operation(summary = "Creates a new Poll", description = "The newly created poll Id will be sent in the location response header")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Poll Created Successfully", content = @Content(schema = @Schema(implementation = Poll.class))),
            @ApiResponse(responseCode = "500", description = "Error creating Poll", content = @Content(schema = @Schema(implementation = ErrorDetailVO.class)))
    })
    public ResponseEntity<PollVO> createPoll(@RequestBody @Valid PollVO pollVO) {
        pollVO = pollService.createPoll(pollVO);
        HttpHeaders httpHeaders = new HttpHeaders();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pollVO.id()).toUri();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(pollVO, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping(path = "/polls/{pollId}")
    @Operation(summary = "Fetches a poll by id", description = "Fetched poll by id will be sent as a response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Poll fetched successfully", content = @Content(schema = @Schema(implementation = Poll.class))),
            @ApiResponse(responseCode = "404", description = "Poll not found for the given id", content = @Content(schema = @Schema(implementation = ErrorDetailVO.class)))
    })
    public ResponseEntity<?> getPoll(@PathVariable(name = "pollId", required = true) Long pollId) {
        return new ResponseEntity<>(pollService.getPollById(pollId), HttpStatus.OK);
    }

    @PutMapping(path = "/polls/{pollId}")
    @Operation(summary = "Updates a poll by id", description = "Updated poll by id will be sent as a response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Poll updated successfully", content = @Content(schema = @Schema(implementation = Poll.class))),
            @ApiResponse(responseCode = "404", description = "Poll not found for the given id", content = @Content(schema = @Schema(implementation = ErrorDetailVO.class)))
    })
    public ResponseEntity<?> updatePoll(@PathVariable(name = "pollId", required = true) Long pollId, @RequestBody @Valid PollVO pollVO) {
        return new ResponseEntity<>(pollService.updatePoll(pollVO, pollId), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(path = "/polls/{pollId}")
    @Operation(summary = "Deletes a poll by id", description = "Deletes a poll by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Poll deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Poll not found for the given id", content = @Content(schema = @Schema(implementation = ErrorDetailVO.class)))
    })
    public ResponseEntity<?> deletePoll(@PathVariable(name = "pollId", required = true) Long pollId) {
        pollService.deletePoll(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
