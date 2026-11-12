package com.pluralsight.practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {
    //tells Spring to supply an object (bean) that Spring manages instead of creating it with "new"
    //dependency injection
    @Autowired
    private MockMvc mockMvc;

    //simulates an HTTP GET request to HelloController, checks that the response status is 200
    //and checks that the response body is exactly that string
    @Test
    void helloReturnsGreeting() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello there!"));
    }

    //sends an HTTP GET request to a path not handled by a controller and checks that the response
    //status is 404
    @Test
    void unknownPathReturnsNotFound() throws Exception {
        mockMvc.perform(get("/hey"))
                .andExpect(status().isNotFound());
    }

    //param() adds the query parameter to the stimulated request, which is equivalent to requesting
    // "/hello?name=Kellsy"
    @Test
    void helloWithNameReturnsPersonalizedGreetings() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Kellsy"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Kellsy"));
    }

    @Test
    void helloWithoutNameReturnsDefaultGreeting() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World"));
    }
}
