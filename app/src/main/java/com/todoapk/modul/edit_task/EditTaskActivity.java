package com.todoapk.modul.edit_task;

import android.util.Log;
import android.view.View;

import com.todoapk.base.BaseFragmentHolderActivity;

import static com.todoapk.utils.Constants.TASK_ID;


public class EditTaskActivity extends BaseFragmentHolderActivity {
    EditTaskFragment editTaskFragment;
//    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        editTaskFragment = new EditTaskFragment();
        String id = getIntent().getExtras().getString(TASK_ID);
        editTaskFragment.setId(id);
        setCurrentFragment(editTaskFragment, true);
    }



}
