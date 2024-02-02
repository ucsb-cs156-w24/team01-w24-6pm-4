package edu.ucsb.cs156.spring.backenddemo.controllers;


import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Zipcode info from Zippopotam")
@Slf4j
@RestController
@RequestMapping("/api/zipcodes")
public class ZipCodeController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ZipCodeQueryService zipCodeQueryService;

    @Operation(summary="Get a zipcode, its country, and places in that zipcode!",description="JSON return format documented here https://api.zippopotam.us/")
    @GetMapping("/get")
    public ResponseEntity<String> getZipCodes(
        @Parameter(name="zipcode",description = "zipcode info of 93117", example="93117") @RequestParam String zipcode
    ) throws JsonProcessingException {
        log.info("getZipCodes: zipcode={}", zipcode);
        String result = zipCodeQueryService.getJSON(zipcode);
        return ResponseEntity.ok().body(result);
    }

}