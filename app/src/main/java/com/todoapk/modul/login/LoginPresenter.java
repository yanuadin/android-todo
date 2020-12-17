package com.todoapk.modul.login;

import android.content.Context;
import android.content.SharedPreferences;

import com.todoapk.data.model.User;
import com.todoapk.data.source.session.SessionRepository;

public class LoginPresenter implements LoginContract.Presenter {
    private final LoginContract.View view;
    private final SessionRepository sessionRepository;

    public LoginPresenter(LoginContract.View view, SessionRepository sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void performLogin(String email, String password) {
        saveUser(email, password);
        view.redirectToMain();
    }

    private void saveUser(String email, String password) {
        User loggedUser = new User(email, password,"TOKEN123456");
        sessionRepository.setSessionData(loggedUser);
    }

    @Override
    public void start() {
        if(sessionRepository.getSessionData() != null){
            view.redirectToMain();
        }
    }
}
