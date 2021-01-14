package com.todoapk.modul.edit_task;

import com.todoapk.base.BasePresenter;
import com.todoapk.base.BaseView;
import com.todoapk.data.model.Task;

/**
 * Created by YAN on 13/03/19.
 */

public interface EditTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToMain();
        void showData(Task task);
        void setId(String id);
    }

    interface Presenter extends BasePresenter {
        void loadData(String id);
        void updateTask(int date, String title, String time);
        void deleteTask();
    }
}
