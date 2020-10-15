package com.todoapk.modul.main;

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
    public void performLogin(final String email, final String password){
        //proses login
        //if login success call redirect to profile
//        view.redirectToProfile();
    }

}
