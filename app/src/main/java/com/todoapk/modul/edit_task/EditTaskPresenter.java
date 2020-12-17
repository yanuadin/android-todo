package com.todoapk.modul.edit_task;

import com.todoapk.data.model.Task;
import com.todoapk.data.source.local.TableHandler;

/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskPresenter implements EditTaskContract.Presenter{
    private final EditTaskContract.View view;
    private final TableHandler tableHandler;
    Task task;

    public EditTaskPresenter(EditTaskContract.View view, TableHandler tableHandler) {
        this.view = view;
        this.tableHandler = tableHandler;
    }

    @Override
    public void start() {}

    @Override
    public void loadData(String id) {
        task = (Task) tableHandler.readById(id);
        view.showData(task);
    }

    @Override
    public void updateTask(String date, String title, String time) {
        task.setDate(date);
        task.setTitle(title);
        task.setTime(time);

        tableHandler.update(task);
        view.redirectToMain();
    }

    @Override
    public void deleteTask() {
        tableHandler.delete(task);

        view.redirectToMain();
    }

}
