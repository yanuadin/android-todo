package com.todoapk.modul.add_task;

import android.view.View;

import com.todoapk.base.BaseFragmentHolderActivity;


public class AddTaskActivity extends BaseFragmentHolderActivity {
    AddTaskFragment addTaskFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        addTaskFragment = new AddTaskFragment();
        setCurrentFragment(addTaskFragment, true);

    }



}
