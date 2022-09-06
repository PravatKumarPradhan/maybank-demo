package com.example.demo.util;

import java.time.Instant;

public class ggg {

    public static void main(String[] args) {
        Instant curentTimr= Instant.now();
        System.out.println(curentTimr);

        long currentTimestamp = Instant.now().toEpochMilli();
        System.out.println(currentTimestamp);
    }
}
