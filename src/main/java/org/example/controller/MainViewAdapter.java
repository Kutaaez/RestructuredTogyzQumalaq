package org.example.controller;

import org.example.view.MainView;

public class MainViewAdapter implements GameObserver {
    private final MainView view;

    public MainViewAdapter(MainView view) {
        this.view = view;
    }

    @Override
    public void update() {
        view.update();
    }
}