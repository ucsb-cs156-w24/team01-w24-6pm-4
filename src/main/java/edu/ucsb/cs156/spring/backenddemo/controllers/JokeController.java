package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.JokeQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Joke Query")
@Slf4j
@RequestMapping("/api/jokes")

@RestController
public class JokeController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    JokeQueryService jokeQueryService;

    @Operation(summary = "Get 'n' number of jokes based on a selected category", description = "Jokes are from /api/jokes/get")
    @GetMapping("/get")

    public ResponseEntity<String> getJokes(
        @Parameter(name="numJokes", description="number of jokes", example="4") @RequestParam String category,
        @Parameter(name="category", description="category of joke", example="Programming") @RequestParam String numJokes
    ) throws JsonProcessingException {
        log.info("getJokes: category={} numJokes={}", category, numJokes);
        String result = jokeQueryService.getJSON(category, numJokes);
        return ResponseEntity.ok().body(result);
    }
    
}
