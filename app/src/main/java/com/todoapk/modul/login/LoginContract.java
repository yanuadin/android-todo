package com.todoapk.modul.login;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.todoapk.base.BasePresenter;
import com.todoapk.base.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToMain();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
        void handleSignInResult(Task<GoogleSignInAccount> completedTask);
    }
}
