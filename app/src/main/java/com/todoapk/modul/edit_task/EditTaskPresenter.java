package com.todoapk.modul.edit_task;

/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskPresenter implements EditTaskContract.Presenter{
    private final EditTaskContract.View view;



    public EditTaskPresenter(EditTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performLogin(final String email, final String password){
        //proses login
        //if login success call redirect to profile
//        view.redirectToProfile();
    }

}
