package com.todoapk.modul.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.todoapk.R;
import com.todoapk.base.BaseFragment;
import com.todoapk.model.Task;
import com.todoapk.modul.add_task.AddTaskActivity;
import com.todoapk.modul.edit_task.EditTaskActivity;
import com.todoapk.utils.RecyclerViewAdapterTodoList;

import static com.todoapk.utils.Constants.TASK_ID;

/**
 * Created by fahrul on 13/03/19.
 */

public class MainFragment extends BaseFragment<MainActivity, MainContract.Presenter> implements MainContract.View {

    Button addActivity;
    RecyclerView rvTask;

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        mPresenter = new MainPresenter(this);
        mPresenter.start();

//        etEmail = fragmentView.findViewById(R.id.et_email);
//        etPassword = fragmentView.findViewById(R.id.et_password);
//        btnLogin = fragmentView.findViewById(R.id.bt_login);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setBtLoginClick();
//            }
//        });
        addActivity = fragmentView.findViewById(R.id.btn_addNew);
        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToAddTask();
            }
        });
        rvTask = fragmentView.findViewById(R.id.rv_task);
        RecyclerViewAdapterTodoList adapterTodoList = new RecyclerViewAdapterTodoList();
        rvTask.setHasFixedSize(true);
        rvTask.setLayoutManager(new LinearLayoutManager(activity));
        rvTask.setAdapter(adapterTodoList);
        adapterTodoList.setTaskList(mPresenter.getTasks());
        adapterTodoList.setTodoListClickListener(new RecyclerViewAdapterTodoList.TodoListClickListener() {
            @Override
            public void onTaskClick(Task task) {
                String id = task.getId();
                mPresenter.editTask(id);
            }

            @Override
            public void onTaskCheckBoxClick(Task task) {

            }
        });

        setTitle("My Login View");

        return fragmentView;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToAddTask() {
            Intent intent = new Intent(activity, AddTaskActivity.class);
            startActivity(intent);
//            activity.finish();
    }

    @Override
    public void redirectToEditTask(String id) {
        Intent intent = new Intent(activity, EditTaskActivity.class);
        intent.putExtra(TASK_ID, id);
        startActivity(intent);
//        activity.finish();
    }
}
