package com.todoapk.modul.edit_task;

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
import com.todoapk.data.model.Task;
import com.todoapk.data.source.local.TaskTableHandler;
import com.todoapk.modul.main.MainActivity;


/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskFragment extends BaseFragment<EditTaskActivity, EditTaskContract.Presenter> implements EditTaskContract.View {

    TextView tv_date;
    EditText et_title;
    EditText et_time;
    Button btnSave;
    Button btnDelete;
    String id;

    public EditTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_edit_task, container, false);
        mPresenter = new EditTaskPresenter(this, new TaskTableHandler(getActivity()));
        mPresenter.start();

        tv_date = fragmentView.findViewById(R.id.date_1);
        et_title = fragmentView.findViewById(R.id.et_title_1);
        et_time = fragmentView.findViewById(R.id.et_time_1);
        btnSave = fragmentView.findViewById(R.id.save_btn);
        btnDelete = fragmentView.findViewById(R.id.delete_btn);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnSaveClick(view);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnDeleteClick(view);
            }
        });
        setTitle("Edit Task");
        mPresenter.loadData(this.id);

        return fragmentView;
    }

    private void setBtnSaveClick(View view) {
        String date = tv_date.getText().toString();
        String title = et_title.getText().toString();
        String time = et_time.getText().toString();
        mPresenter.updateTask(date,title,time);
    }

    private void setBtnDeleteClick(View view) {
        mPresenter.deleteTask();
    }

    @Override
    public void setPresenter(EditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToMain() {
        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void showData(Task task) {
        this.tv_date.setText(task.getDate());
        this.et_title.setText(task.getTitle());
        this.et_time.setText(task.getTime());
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }


}
