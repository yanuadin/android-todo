package com.todoapk.modul.main;

import android.view.View;

import com.todoapk.base.BaseFragmentHolderActivity;


public class MainActivity extends BaseFragmentHolderActivity {
    MainFragment mainFragment;
//    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        mainFragment = new MainFragment();
        setCurrentFragment(mainFragment, false);

    }



}
