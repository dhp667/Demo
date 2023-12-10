package com.example.demo.test.react;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 5);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> null); // Simulating a null result

        CompletableFuture<Integer> composedFuture1 = future1.thenCompose(result1 -> {
            if (result1 != null) {
                return CompletableFuture.supplyAsync(() -> result1 + 10);
            } else {
                return CompletableFuture.completedFuture(0); // Returning a default value for null result
            }
        });

        CompletableFuture<Integer> composedFuture2 = future2.thenCompose(result2 -> {
            if (result2 != null) {
                return CompletableFuture.supplyAsync(() -> result2 + 10);
            } else {
                return CompletableFuture.completedFuture(0); // Returning a default value for null result
            }
        });

        System.out.println("Composed Future 1 Result: " + composedFuture1.get()); // Output: Composed Future 1 Result: 15
        System.out.println("Composed Future 2 Result: " + composedFuture2.get()); // Output: Composed Future 2 Result: 0
    }


}
