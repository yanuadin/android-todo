package com.todoapk.modul.main;

import com.todoapk.base.BasePresenter;
import com.todoapk.base.BaseView;
import com.todoapk.data.model.Task;

import java.util.ArrayList;
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
        ArrayList<Task> getTasks();
    }
}
