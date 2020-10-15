package com.todoapk.modul.main;

import com.todoapk.base.BasePresenter;
import com.todoapk.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void redirectToAddTask();
        void redirectToEditTask();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
    }
}
