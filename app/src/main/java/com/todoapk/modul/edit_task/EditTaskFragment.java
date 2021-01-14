package com.todoapk.modul.edit_task;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.todoapk.R;
import com.todoapk.base.BaseFragment;
import com.todoapk.data.model.Task;
import com.todoapk.data.source.local.TaskTableHandler;
import com.todoapk.databinding.FragmentAddTaskBinding;
import com.todoapk.databinding.FragmentEditTaskBinding;
import com.todoapk.modul.main.MainActivity;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


/**
 * Created by YAN on 13/03/19.
 */

public class EditTaskFragment extends BaseFragment<EditTaskActivity, EditTaskContract.Presenter>
        implements EditTaskContract.View, View.OnClickListener {

    private FragmentEditTaskBinding binding;
    private int selectedDate = 0;
    private TimePickerDialog timePickerDialog;
    private String id;
    private Task task;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_edit_task, container, false);
        binding = FragmentEditTaskBinding.inflate(getLayoutInflater());
        mPresenter = new EditTaskPresenter(this, new TaskTableHandler(getActivity()));
        fragmentView = binding.getRoot();
        mPresenter.start();

        initView();

        return fragmentView;
    }

    private void initView() {
        setTitle("Edit Task");
        binding.etTime.setOnClickListener(this);
        binding.saveBtn.setOnClickListener(this);
        binding.deleteBtn.setOnClickListener(this);

        mPresenter.loadData(this.id);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.selectedDate = Calendar.getInstance().getTime().getDate();
        initCalender();
    }

    private void initCalender() {
        /*** Set Up Calendar ***/
        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, 0);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        /* selected date */
        Calendar selectedDateDB = Calendar.getInstance();
        int deviationDate = Integer.parseInt(task.getDate()) - startDate.getTime().getDate();
        selectedDateDB.add(Calendar.DATE, deviationDate);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(getActivity(), R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .defaultSelectedDate(selectedDateDB)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                selectedDate = date.getTime().getDate();
            }
        });
    }

    private void showTimeDialog() {
        /* Selected Time DB*/
        String[] timeDB = binding.etTime.getText().toString().split(":");

        Calendar calendar = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(getActivity(), R.style.TimePickerThemeYAN , new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String min = String.valueOf(minute);
                String hour = String.valueOf(hourOfDay);

                //Formatting lead zero
                min = minute < 10 ? "0" + min : min;
                hour = hourOfDay < 10 ? "0" + hour : hour;

                binding.etTime.setText(hour + ":" + min);
            }
        },
                Integer.valueOf(timeDB[0]), Integer.valueOf(timeDB[1]),
                DateFormat.is24HourFormat(getActivity())
        );

        timePickerDialog.show();
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
        this.task = task;
        binding.etTitle.setText(task.getTitle());
        binding.etTime.setText(task.getTime());
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == binding.saveBtn.getId()) {
            String title = binding.etTitle.getText().toString();
            String time = binding.etTime.getText().toString();
            mPresenter.updateTask(this.selectedDate,title,time);
        } else if (v.getId() == binding.deleteBtn.getId()) {
            mPresenter.deleteTask();
        } else if (v.getId() == binding.etTime.getId()) {
            showTimeDialog();
        }
    }
}
