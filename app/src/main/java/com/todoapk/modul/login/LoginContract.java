package com.todoapk.modul.login;

import com.todoapk.base.BasePresenter;
import com.todoapk.base.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToMain();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
    }
}
