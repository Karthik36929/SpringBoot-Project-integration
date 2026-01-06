package com.example.addingnumbers.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdditionController {

  @GetMapping("/add")
  public Map<String,Object> addTwoNumbers(@RequestParam int a, @RequestParam int b) {
    int result = a + b;
    Map<String,Object> response = new HashMap<>();
    response.put("message", "Addition completed successfully");
    response.put("operand1", a);
    response.put("operand2", b);
    response.put("result", result);
    return response;
  }

  @PostMapping("/add/list")
  public Map<String,Object> addListOfNumbers(@RequestBody List<Integer> numbers) {
    int sum = 0;
    for (Integer num : numbers) {
    sum += num;
    }
    Map<String,Object> response = new HashMap<>();
    response.put("message", "Sum calculated successfully");
    response.put("count", numbers.size());
    response.put("numbers", numbers);
    response.put("sum", sum);
    return response;
  }

  @PostMapping("/add/batch")
  public Map<String,Object> addBatchOperations(@RequestBody Map<String,Object> request) {
    List<Integer> numbers = (List<Integer>) request.get("numbers");
    int sum = calculateSum(numbers);
    double average = calculateAverage(numbers);
    Map<String,Object> response = new HashMap<>();
    response.put("message", "Batch addition completed");
    response.put("sum", sum);
    response.put("average", average);
    response.put("count", numbers.size());
    return response;
  }

  @GetMapping("/add/range")
  public Map<String,Object> addRange(@RequestParam int start, @RequestParam int end) {
    List<Integer> numbers = new ArrayList<>();
    for (int i = start; i <= end; i++) {
    numbers.add(i);
    }
    int sum = 0;
    for (Integer num : numbers) {
    sum += num;
    }
    Map<String,Object> response = new HashMap<>();
    response.put("message", "Range sum calculated");
    response.put("start", start);
    response.put("end", end);
    response.put("sum", sum);
    response.put("count", numbers.size());
    return response;
  }

  @PostMapping("/add/weighted")
  public Map<String,Object> addWeightedNumbers(@RequestBody Map<String,Object> data) {
    List<Integer> numbers = (List<Integer>) data.get("numbers");
    List<Integer> weights = (List<Integer>) data.get("weights");
    int weightedSum = calculateWeightedSum(numbers, weights);
    Map<String,Object> response = new HashMap<>();
    response.put("message", "Weighted sum calculated");
    response.put("weightedSum", weightedSum);
    response.put("count", numbers.size());
    return response;
  }
  // Helper methods
  private int calculateSum(List<Integer> numbers) {
    int sum = 0;
    for (Integer num : numbers) {
    sum += num;
  }
    return sum;
  }

  private double calculateAverage(List<Integer> numbers) {
    if (numbers.isEmpty()) {
    return 0.0;
  }
    int sum = calculateSum(numbers);
    return (double) sum / numbers.size();
  }

  private int calculateWeightedSum(List<Integer> numbers, List<Integer> weights) {
    int sum = 0;
    for (int i = 0; i < numbers.size() && i < weights.size(); i++) {
    sum += numbers.get(i) * weights.get(i);
  }
    return sum;
  }
}
