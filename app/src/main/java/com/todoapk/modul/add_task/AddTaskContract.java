package com.todoapk.modul.add_task;

import com.todoapk.base.BasePresenter;
import com.todoapk.base.BaseView;

/**
 * Created by YAN on 13/03/19.
 */

public interface AddTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToMain();
    }

    interface Presenter extends BasePresenter {
        void addTask(int date, String title, String time);
    }
}
