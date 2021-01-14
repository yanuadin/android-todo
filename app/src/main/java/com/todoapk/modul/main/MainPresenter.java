package com.todoapk.modul.main;

import android.content.Intent;
import android.util.Log;

import com.todoapk.R;
import com.todoapk.data.source.local.TableHandler;
import com.todoapk.data.source.session.SessionRepository;
import com.todoapk.data.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YAN on 13/03/19.
 */

public class MainPresenter implements MainContract.Presenter{
    private final MainContract.View view;
    private final SessionRepository sessionRepository;
    private final TableHandler tableHandler;

    public MainPresenter(MainContract.View view, SessionRepository sessionRepository, TableHandler tableHandler) {
        this.view = view;
        this.sessionRepository = sessionRepository;
        this.tableHandler = tableHandler;
    }

    @Override
    public void start() {}

    @Override
    public ArrayList<Task> getTasks() {
        // This should be getting data from DB
        ArrayList<Task> tasks = tableHandler.readAll();
        return tasks;
    }

    @Override
    public void updateStatus(String id) {
        Task task = (Task) tableHandler.readById(id);
        task.setStatus(1);
        tableHandler.update(task);
    }

    @Override
    public void shareTask(String id) {
        Task task = (Task) tableHandler.readById(id);
        String taskText =
                "To-do : " + task.getTitle() + "\n" +
                "Date : " + task.getDate() + " Jan 2021" + "\n" +
                "Time : " + task.getTime();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, taskText);
        sendIntent.setType("text/plain");
        view.shareIntent(sendIntent);
    }
}
