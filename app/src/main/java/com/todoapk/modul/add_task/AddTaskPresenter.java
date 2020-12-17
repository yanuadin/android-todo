package com.todoapk.modul.add_task;

import com.todoapk.data.source.local.TableHandler;
import com.todoapk.data.source.session.SessionRepository;
import com.todoapk.data.model.Task;

/**
 * Created by fahrul on 13/03/19.
 */

public class AddTaskPresenter implements AddTaskContract.Presenter{
    private final AddTaskContract.View view;
    private final SessionRepository sessionRepository;                                              //new
    private final TableHandler tableHandler;

    public AddTaskPresenter(AddTaskContract.View view, SessionRepository sessionRepository, TableHandler tableHandler) {
        this.view = view;
        this.sessionRepository = sessionRepository;
        this.tableHandler = tableHandler;
    }

    @Override
    public void start() {}

    @Override
    public void addTask(String date, String title, String time){
        Task newTask = new Task(date, title, time);
        tableHandler.create(newTask);

        view.redirectToMain();
    }

}
