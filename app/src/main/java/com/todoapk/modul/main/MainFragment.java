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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.todoapk.R;
import com.todoapk.base.BaseFragment;
import com.todoapk.data.source.local.TaskTableHandler;
import com.todoapk.data.source.session.TaskSessionRepository;
import com.todoapk.data.model.Task;
import com.todoapk.databinding.ActivityLoginBinding;
import com.todoapk.databinding.FragmentMainBinding;
import com.todoapk.modul.add_task.AddTaskActivity;
import com.todoapk.modul.edit_task.EditTaskActivity;
import com.todoapk.utils.RecyclerViewAdapterTodoList;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.todoapk.utils.Constants.TASK_ID;

/**
 * Created by YAN on 13/03/19.
 */

public class MainFragment extends BaseFragment<MainActivity, MainContract.Presenter>
        implements MainContract.View, View.OnClickListener, RecyclerViewAdapterTodoList.TodoListClickListener {

    private FragmentMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        fragmentView = binding.getRoot();
        mPresenter = new MainPresenter(this, new TaskSessionRepository(getActivity()), new TaskTableHandler(getActivity()));
        mPresenter.start();

        initView();

        return fragmentView;
    }

    private void initView() {
        setTitle("To-Do List");

        Calendar calendar = Calendar.getInstance();
//        binding.tanggal.setText(String.valueOf(calendar.get(Calendar.DATE)));
//        binding.day.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
        binding.tvTitle.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " +
                String.valueOf(calendar.get(Calendar.YEAR)));

        //Sum of task
        int sum = 0;
        for(Task task : mPresenter.getTasks()){
            if(task.getDate().equals(String.valueOf(calendar.getTime().getDate())))
                sum++;
        }
        binding.sumOfTask.setText(sum + " Task");

        RecyclerViewAdapterTodoList adapterTodoList = new RecyclerViewAdapterTodoList();
        binding.rvTask.setHasFixedSize(true);
        binding.rvTask.setLayoutManager(new LinearLayoutManager(activity));
        binding.rvTask.setAdapter(adapterTodoList);
        adapterTodoList.setTaskList(mPresenter.getTasks());

        adapterTodoList.setTodoListClickListener(this);
        binding.btnAddNew.setOnClickListener(this);
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
    }

    @Override
    public void shareIntent(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnAddNew.getId()){
            redirectToAddTask();
        }
    }

    @Override
    public void onTaskClick(Task task) {
        redirectToEditTask(task.getId());
    }

    @Override
    public void onTaskCheckBoxClick(Task task) {
        mPresenter.updateStatus(task.getId());
    }

    @Override
    public void onShareClick(Task task) {
        mPresenter.shareTask(task.getId());
    }
}
