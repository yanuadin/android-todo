package com.todoapk.modul.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.todoapk.R;
import com.todoapk.base.BaseFragment;
import com.todoapk.data.source.session.UserSessionRepository;
import com.todoapk.databinding.ActivityLoginBinding;
import com.todoapk.modul.main.MainActivity;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter>
        implements LoginContract.View, View.OnClickListener {

    private ActivityLoginBinding binding;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        fragmentView = inflater.inflate(R.layout.activity_login, container, false);
        fragmentView = binding.getRoot();
        mPresenter = new LoginPresenter(this, new UserSessionRepository(getActivity()));
        mPresenter.start();

        initView();

        return fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if(account != null){
            Toast.makeText(
                    getActivity().getApplicationContext(),
                    "Selamat datang " + account.getDisplayName(),
                    Toast.LENGTH_SHORT
            ).show();

            this.redirectToMain();
        }
    }

    private void initView() {
        setTitle(getResources().getString(R.string.login_title));
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        binding.btLogin.setOnClickListener(this);
        binding.btSignInGoogle.setOnClickListener(this);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            mPresenter.handleSignInResult(task);
        }
    }

    @Override
    public void redirectToMain() {
        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btLogin.getId()){
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            mPresenter.performLogin(email, password);
        } else if(v.getId() == binding.btSignInGoogle.getId()){
            signIn();
        }
    }
}
