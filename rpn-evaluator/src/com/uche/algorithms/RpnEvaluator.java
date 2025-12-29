package com.uche.algorithms;

import java.util.*;

public class RpnEvaluator {
  public static void main(String[] args) {
    // Array of test cases
    String[][] testCases = {
        // { "5", "3", "+" },
        // { "15", "7", "1", "1", "+", "-", "/", "3", "*", "2", "1", "1", "+", "+", "-" },
        // { "2", "3", "*", "5", "+" },
        // { "*", "4", "+", "13", "5", "/", "+" },
        { "5", "X", "+" }
    };

    for (int i = 0; i < testCases.length; i++) {
      System.out.println("Result " + (i + 1) + ": " + evaluateRpn1(testCases[i]));
    }
  }

  public static int evaluateRpn(String[] tokens) {
    Deque<Integer> stack = new ArrayDeque<>();
    for (String token : tokens) {
      if (token.equals("+")) {
        if (stack.size() < 2) {
          throw new IllegalArgumentException("Insufficient operands for '+' operation");
        }
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a + b);
      } else if (token.equals("-")) {
        if (stack.size() < 2) {
          throw new IllegalArgumentException("Insufficient operands for '-' operation");
        }
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a - b);
      } else if (token.equals("*")) {
        if (stack.size() < 2) {
          throw new IllegalArgumentException("Insufficient operands for '*' operation");
        }
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a * b);
      } else if (token.equals("/")) {
        if (stack.size() < 2) {
          throw new IllegalArgumentException("Insufficient operands for '/' operation");
        }
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a / b);
      } else {
        stack.push(Integer.parseInt(token));
      }
    }

    if (stack.size() != 1) {
      throw new IllegalArgumentException("Invalid RPN expression");
    }

    return stack.pop();
  }

  public static int evaluateRpn1(String[] tokens) {
    Deque<Integer> stack = new ArrayDeque<>();
    for (String token : tokens) {
      switch (token) {
        case "+" -> {
          if (stack.size() < 2)
            throw new IllegalArgumentException("Invalid RPN: too few operands for '+'");
          stack.push(stack.pop() + stack.pop());
        }
        case "-" -> {
          if (stack.size() < 2)
            throw new IllegalArgumentException("Invalid RPN: too few operands for '-'");
          int b = stack.pop();
          int a = stack.pop();
          stack.push(a - b);
        }
        case "*" -> {
          if (stack.size() < 2)
            throw new IllegalArgumentException("Invalid RPN: too few operands for '*'");
          stack.push(stack.pop() * stack.pop());
        }
        case "/" -> {
          if (stack.size() < 2)
            throw new IllegalArgumentException("Invalid RPN: too few operands for '/'");
          int b = stack.pop();
          int a = stack.pop();
          stack.push(a / b);
        }
        default -> {
          // Validate token is an integer
          try {
            stack.push(Integer.parseInt(token));
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid token in RPN expression: '" + token + "'");
          }
        }
      }
    }

    if (stack.size() != 1) {
      throw new IllegalArgumentException("Invalid RPN: leftover values in stack");
    }

    return stack.pop();
  }

}