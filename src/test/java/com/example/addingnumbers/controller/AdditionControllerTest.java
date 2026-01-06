package com.example.addingnumbers.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdditionController.class)
class AdditionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AdditionController controller;

  @Test
  void testAddTwoNumbers() throws Exception {
    // Test GET /add with query parameters
    mockMvc.perform(get("/api/add")
            .param("a", "1")
            .param("b", "1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists());
  }

  @Test
  void testAddListOfNumbers() throws Exception {
    // Test POST /add/list
    String requestBody = "[1,2,3]";
    mockMvc.perform(post("/api/add/list")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists());
  }

  @Test
  void testAddBatchOperations() throws Exception {
    // Test POST /add/batch
    String requestBody = "{\"numbers\":[1,2,3]}";
    mockMvc.perform(post("/api/add/batch")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists());
  }

  @Test
  void testAddRange() throws Exception {
    // Test GET /add/range with query parameters
    mockMvc.perform(get("/api/add/range")
            .param("start", "1")
            .param("end", "1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists());
  }

  @Test
  void testAddWeightedNumbers() throws Exception {
    // Test POST /add/weighted
    String requestBody = "{\"numbers\":[1,2,3], \"weights\":[1,2,3]}";
    mockMvc.perform(post("/api/add/weighted")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists());
  }
}
