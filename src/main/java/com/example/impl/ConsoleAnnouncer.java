package com.example.impl;

import com.example.annotation.InjectByType;
import com.example.service.Announcer;
import com.example.service.Recommendator;

public class ConsoleAnnouncer implements Announcer {
    @InjectByType
    private Recommendator recommendator;
    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
