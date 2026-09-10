# Frog Jump - Technical Assessment

## Overview
This repository contains the solution for the "Frog Jump" algorithmic challenge. The goal of the algorithm is to determine if a frog can successfully cross a river by landing exactly on the last stone, given a set of movement constraints where each subsequent jump size depends on the previous jump.

## Problem Analysis & Approach
A naive brute-force approach (DFS) would attempt all possible jump combinations ($k-1$, $k$, $k+1$). However, given the constraint of up to 2000 stones, this would lead to an exponential time complexity and cause a Time Limit Exceeded (TLE) error due to recalculating overlapping subproblems.

To optimize this, I implemented a **Dynamic Programming (Top-Down approach with Memoization)** solution:

1. **State Definition:** The frog's current state is uniquely identified by two variables: the `current_stone_index` and the `last_jump_size` ($k$).
2. **Memoization (Caching):** I utilized a 2D `Boolean` array (`memo`) to store the results of previously computed states. If the frog reaches a specific stone with a specific jump speed that has already been evaluated as a dead-end, the algorithm returns `false` immediately in $O(1)$ time, pruning that branch of the execution tree.
3. **Optimized Search:** Instead of iterating through the entire array to find if a landing spot has a stone, I mapped the stones' positions to their array indices using a `HashMap`. This reduces the lookup time for the next stone from $O(N)$ to $O(1)$.

## Complexity Analysis

* **Time Complexity:** `O(N^2)`
  Where `N` is the number of stones. In the worst-case scenario, the frog might visit every stone with up to `N` different jump sizes. Thanks to memoization, each state is computed only once. The `HashMap` ensures that checking the next stone's existence takes constant `O(1)` time.
* **Space Complexity:** `O(N^2)`
  The 2D memoization array requires `N * (N + 1)` space. Additionally, the `HashMap` takes `O(N)` space to store the stone positions, and the recursive call stack can reach a depth of `N`. Overall, the space complexity is bounded by `O(N^2)`, which safely fits within standard memory limits for `N = 2000`.

## How to Run
1. Ensure you have [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) installed.
2. Clone this repository.
3. Compile the code using the terminal:
   `javac Main.java Solution.java`
4. Execute the program:
   `java Main`

The `Main` class includes the two provided examples to verify the output against the expected results.