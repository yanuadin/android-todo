package com.todoapk.modul.edit_task;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.todoapk.MainActivity;
import com.todoapk.R;
import com.todoapk.base.BaseFragment;


/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskFragment extends BaseFragment<EditTaskActivity, EditTaskContract.Presenter> implements EditTaskContract.View {

    EditText etEmail;
    EditText etPassword;
    Button btnSubmit;


    public EditTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_edit_task, container, false);
        mPresenter = new EditTaskPresenter(this);
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
        btnSubmit = fragmentView.findViewById(R.id.submit_btn3);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToMain();
            }
        });

        setTitle("Profile");

        return fragmentView;
    }

//    public void setBtLoginClick(){
//        String email = etEmail.getText().toString();
//        String password = etPassword.getText().toString();
//        mPresenter.performLogin(email,password);
//    }

    @Override
    public void setPresenter(EditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToMain() {
        activity.onBackPressed();
    }


}
