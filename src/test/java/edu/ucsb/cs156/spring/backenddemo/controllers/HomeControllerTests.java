package edu.ucsb.cs156.spring.backenddemo.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void test_getHome() throws Exception {
        String expectedBody = HomeController.getHomePageObjectJSON("http://localhost/");
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedBody)));
    }

    @Test
    public void test_getHomePageObjectJSON() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("greeting", "Greetings from Spring Boot!");

        String baseUrl = "http://localhost:8080/";

        List<String> team = new ArrayList<String>();
        team.add("David J.");
        team.add("Aavash A.");
        team.add("Luxiang Z.");
        team.add("Derek C.");
        team.add("Ryan V.");
        team.add("Bao T.");
        resultMap.put("team", team);
        resultMap.put("repo", "https://github.com/ucsb-cs156-w24/team01-w24-6pm-4");
        resultMap.put("api-documentation", baseUrl + "swagger-ui/index.html");
        String expected = mapper.writeValueAsString(resultMap);
        String actual = HomeController.getHomePageObjectJSON("http://localhost:8080/");

        assertEquals(expected, actual);
    }

}
