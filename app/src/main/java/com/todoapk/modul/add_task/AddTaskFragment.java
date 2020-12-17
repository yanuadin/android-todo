package com.todoapk.modul.add_task;

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
import com.todoapk.data.source.local.TaskTableHandler;
import com.todoapk.data.source.session.TaskSessionRepository;
import com.todoapk.modul.main.MainActivity;


/**
 * Created by fahrul on 13/03/19.
 */

public class AddTaskFragment extends BaseFragment<AddTaskActivity, AddTaskContract.Presenter> implements AddTaskContract.View {

    TextView tv_date;
    EditText et_title;
    EditText et_time;
    Button btnSubmit;

    public AddTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_add_task, container, false);
        mPresenter = new AddTaskPresenter(this, new TaskSessionRepository(getActivity()), new TaskTableHandler(getActivity()));
        mPresenter.start();

        tv_date = fragmentView.findViewById(R.id.date_1);
        et_title = fragmentView.findViewById(R.id.et_title_1);
        et_time = fragmentView.findViewById(R.id.et_time_1);
        btnSubmit = fragmentView.findViewById(R.id.submit_btn);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnSubmitClick(view);
            }
        });

        setTitle("Add Task");

        return fragmentView;
    }

    private void setBtnSubmitClick(View view) {
        String date = tv_date.getText().toString();
        String title = et_title.getText().toString();
        String time = et_time.getText().toString();
        mPresenter.addTask(date,title,time);
    }

//    public void setBtLoginClick(){
//        String email = etEmail.getText().toString();
//        String password = etPassword.getText().toString();
//        mPresenter.performLogin(email,password);
//    }

    @Override
    public void setPresenter(AddTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToMain() {
        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
        activity.finish();
    }


}
