package com.todoapk.modul.main;

import com.todoapk.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fahrul on 13/03/19.
 */

public class MainPresenter implements MainContract.Presenter{
    private final MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void addTask() {
        view.redirectToAddTask();
    }

    @Override
    public void editTask(String id) {
        view.redirectToEditTask(id);
    }

    @Override
    public List<Task> getTasks() {
        // This should be getting data from DB
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("1", "2020/11/05", "Buy a pack of coffe", "08.00"));
        tasks.add(new Task("2", "2020/11/05", "Meet with client", "13.00"));
        tasks.add(new Task("3", "2020/11/05", "Dinner", "19.00"));
        tasks.add(new Task("4", "2020/11/05", "Playing Gmae", "22.00"));
        tasks.add(new Task("5", "2020/11/05", "Buy a pack of coffe", "08.00"));
        tasks.add(new Task("6", "2020/11/05", "Buy a pack of coffe", "08.00"));
        tasks.add(new Task("7", "2020/11/05", "Buy a pack of coffe", "08.00"));

        return tasks;
    }


}
