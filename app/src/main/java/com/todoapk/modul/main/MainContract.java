package com.todoapk.modul.main;

import android.content.Intent;

import com.todoapk.base.BasePresenter;
import com.todoapk.base.BaseView;
import com.todoapk.data.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YAN on 13/03/19.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void redirectToAddTask();
        void redirectToEditTask(String id);
        void shareIntent(Intent intent);
    }

    interface Presenter extends BasePresenter {
        ArrayList<Task> getTasks();
        void updateStatus(String id);
        void shareTask(String id);
    }
}
