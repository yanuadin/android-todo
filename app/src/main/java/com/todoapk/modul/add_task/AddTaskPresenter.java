package com.todoapk.modul.add_task;

/**
 * Created by fahrul on 13/03/19.
 */

public class AddTaskPresenter implements AddTaskContract.Presenter{
    private final AddTaskContract.View view;

    public AddTaskPresenter(AddTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void addTask(String date, String title, String time){
        view.redirectToMain();
    }

}
