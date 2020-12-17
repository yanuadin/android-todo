package com.todoapk.modul.main;

import android.util.Log;

import com.todoapk.data.source.local.TableHandler;
import com.todoapk.data.source.session.SessionRepository;
import com.todoapk.data.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fahrul on 13/03/19.
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


}
