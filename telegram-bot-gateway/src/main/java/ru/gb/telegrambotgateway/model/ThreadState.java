package ru.gb.telegrambotgateway.model;

import lombok.Data;

@Data
public class ThreadState {
    private Thread thread;
    private boolean stop;

    public ThreadState(Thread thread) {
        this.thread = thread;
    }
}
