package com.todoapk.modul.add_task;

import com.todoapk.base.BasePresenter;
import com.todoapk.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface AddTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToMain();
    }

    interface Presenter extends BasePresenter {
//        void performLogin(String email, String password);
        void addTask(String date, String title, String time);
    }
}
