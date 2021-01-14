package com.todoapk.modul.add_task;

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
import androidx.core.content.ContextCompat;

import com.todoapk.R;
import com.todoapk.base.BaseFragment;
import com.todoapk.data.source.local.TaskTableHandler;
import com.todoapk.data.source.session.TaskSessionRepository;
import com.todoapk.databinding.ActivityLoginBinding;
import com.todoapk.databinding.FragmentAddTaskBinding;
import com.todoapk.modul.main.MainActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


/**
 * Created by YAN on 13/03/19.
 */

public class AddTaskFragment extends BaseFragment<AddTaskActivity, AddTaskContract.Presenter>
        implements AddTaskContract.View, View.OnClickListener {

    private FragmentAddTaskBinding binding;
    private int selectedDate = 0;
    private TimePickerDialog timePickerDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_add_task, container, false);
        binding = FragmentAddTaskBinding.inflate(getLayoutInflater());
        mPresenter = new AddTaskPresenter(this, new TaskSessionRepository(getActivity()), new TaskTableHandler(getActivity()));
        fragmentView = binding.getRoot();
        mPresenter.start();

        initView();

        return fragmentView;
    }

    private void initView() {
        setTitle("Add Task");
        binding.submitBtn.setOnClickListener(this);
        binding.etTime.setOnClickListener(this);
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

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(getActivity(), R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                selectedDate = date.getTime().getDate();
            }
        });
    }

    private void showTimeDialog() {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(getActivity(), R.style.TimePickerThemeYAN , new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String min = String.valueOf(minute);
                String hour = String.valueOf(hourOfDay);

                min = minute < 10 ? "0" + min : min;
                hour = hourOfDay < 10 ? "0" + hour : hour;
                binding.etTime.setText(hour + ":" + min);
            }
        },
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(getActivity())
        );

        timePickerDialog.show();
    }

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


    @Override
    public void onClick(View v){
        if (v.getId() == binding.submitBtn.getId()) {
            String title = binding.etTitle.getText().toString();
            String time = binding.etTime.getText().toString();

            mPresenter.addTask(selectedDate,title,time);
        } else if (v.getId() == binding.etTime.getId()) {
            showTimeDialog();
        }
    }
}
