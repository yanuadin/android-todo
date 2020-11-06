package com.todoapk.modul.add_task;

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
        mPresenter = new AddTaskPresenter(this);
        mPresenter.start();

        tv_date = fragmentView.findViewById(R.id.date_1);
        et_title = fragmentView.findViewById(R.id.et_title_1);
        et_time = fragmentView.findViewById(R.id.et_time_1);
        btnSubmit = fragmentView.findViewById(R.id.delete_btn);
//        etEmail = fragmentView.findViewById(R.id.et_email);
//        etPassword = fragmentView.findViewById(R.id.et_password);
//        btnLogin = fragmentView.findViewById(R.id.bt_login);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setBtLoginClick();
//            }
//        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnSubmitClick(view);
            }
        });

        setTitle("Profile");

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
        activity.onBackPressed();
    }


}
