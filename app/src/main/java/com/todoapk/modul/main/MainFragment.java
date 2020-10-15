package com.todoapk.modul.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.todoapk.R;
import com.todoapk.base.BaseFragment;
import com.todoapk.modul.add_task.AddTaskActivity;
import com.todoapk.modul.edit_task.EditTaskActivity;


/**
 * Created by fahrul on 13/03/19.
 */

public class MainFragment extends BaseFragment<MainActivity, MainContract.Presenter> implements MainContract.View {

    EditText etEmail;
    EditText etPassword;
    Button addActivity;
    TextView editActivity;


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
        editActivity = fragmentView.findViewById(R.id.title_1);
        editActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToEditTask();
            }
        });

        setTitle("My Login View");

        return fragmentView;
    }

    public void setBtLoginClick(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        mPresenter.performLogin(email,password);
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
    public void redirectToEditTask() {
        Intent intent = new Intent(activity, EditTaskActivity.class);
        startActivity(intent);
//        activity.finish();
    }
}
