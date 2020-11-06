package com.todoapk.modul.main;

import com.todoapk.base.BasePresenter;
import com.todoapk.base.BaseView;
import com.todoapk.model.Task;

import java.util.List;

/**
 * Created by fahrul on 13/03/19.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void redirectToAddTask();
        void redirectToEditTask(String id);
    }

    interface Presenter extends BasePresenter {
//        void performLogin(String email, String password);
        void addTask();
        void editTask(String id);

        List<Task> getTasks();
    }
}
