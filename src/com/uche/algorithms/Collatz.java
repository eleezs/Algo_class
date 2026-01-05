package com.uche.algorithms;

import java.util.*;

public class Collatz {
  public static void main(String[] args) {
    // int[] testArrays = { 13, 10 };
    // int testArrays = 1000000;

    // for (int i = 0; i < testArrays.length; i++) {
    //   System.out.println("Collatz length of " + testArrays[i] + " is: " +
    //       collatzLength(testArrays[i]));
    //   // System.out.println("Collatz steps of " + testArrays[i] + " is: " +
    //   // collatzSteps(testArrays[i]));
    // }

    System.out.println("Number with longest Collatz sequence under 1,000,000 is: " + collatzNumberWithMaxLength(1000000));
  }

  // Implement the Collatz conjecture algorithm
  // this function takes a positive integer n and returns the number of steps
  // to reach 1 and including 1
  public static int collatzLength(long n) {
    int length = 1;

    while (n != 1) {
      System.out.println("n = " + n);
      if (n % 2 == 0) {
        n = n / 2;
      } else {
        n = 3 * n + 1;
      }
      length++;
    }
    return length;
  }

  // Implement the Collatz conjecture algorithm
  // this function takes a positive integer n and returns the number of steps
  // to reach 1 excluding 1
  public static int collatzSteps(long n) {
    int steps = 0;
    while (n != 1) {
      if (n % 2 == 0) {
        n = n / 2;
      } else {
        n = 3 * n + 1;
      }
      steps++;
    }
    return steps;
  }

  public static int collatzNumberWithMaxLength(long n) {
    int maxlength = 0;
    int numberWithMaxLength = 0;
    int length = 1;
    Map<Long, Integer> cache = new HashMap<>();

    for (long i = 1; i <= n; i++) {
      length = 1;
      long current = i;
      List<Long> visited = new ArrayList<>();

      while (current != 1) {
        if (cache.containsKey(current)) {
          length += cache.get(current) - 1; // subtract 1 to avoid double counting
          break;
        }

        visited.add(current);
        if (current % 2 == 0) {
          current = current / 2;
        } else {
          current = 3 * current + 1;
        }
        length++;
      }

      if (length > maxlength) {
        maxlength = length;
        numberWithMaxLength = (int) i;
      }

      for (Long num : visited) {
        cache.put(num, length);
        length--;
      }

    }

    return numberWithMaxLength;
  }

  public static int collatzNumberWithMaxLength1(int limit) {
    int maxLength = 0;
    int numberWithMaxLength = 0;

    Map<Long, Integer> cache = new HashMap<>();
    cache.put(1L, 1); // base case

    for (long i = 1; i <= limit; i++) {
        long current = i;
        int length = 0;

        List<Long> visited = new ArrayList<>();

        // Walk the sequence
        while (!cache.containsKey(current)) {
            visited.add(current);
            length++;
            if (current % 2 == 0) {
                current = current / 2;
            } else {
                current = 3 * current + 1;
            }
        }

        // Add the cached length for current
        length += cache.get(current);

        // Assign lengths backward for all visited numbers
        int tempLength = length;
        for (Long v : visited) {
            cache.put(v, tempLength);
            tempLength--;
        }

        if (length > maxLength) {
            maxLength = length;
            numberWithMaxLength = (int) i;
        }
    }

    return numberWithMaxLength;
}

}
