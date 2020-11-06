package com.todoapk.modul.login;

import android.content.Context;
import android.content.SharedPreferences;
import static com.todoapk.utils.Constants.EMAIL_KEY;
import static com.todoapk.utils.Constants.PASSWORD_KEY;
import static com.todoapk.utils.Constants.PREFERENCE_KEY;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    private final SharedPreferences sharedPreferences;

    public LoginPresenter(LoginContract.View view, Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
        this.view = view;
    }

    @Override
    public void performLogin(String email, String password) {
        saveUser(email, password);
        view.redirectToMain();
    }

    private void saveUser(String email, String password) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(EMAIL_KEY, email);
        editor.putString(PASSWORD_KEY, password);
        editor.commit();
    }

    @Override
    public void start() {

    }
}
