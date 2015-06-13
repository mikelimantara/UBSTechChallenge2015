package com.prototype.ubs.techchallenge.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.utils.Constants;

/**
 * Created by Michael on 12/6/2015.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private OnLoginListener loginListener;
    private View v = null;
    private EditText edtxUsername = null;
    private EditText edtxPassword = null;
    private Button btnLogin = null;
    private SharedPreferences sharedPrefs = null;

    public interface OnLoginListener {
        public void onLogin();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            loginListener = (OnLoginListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onLoginListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.login, container, false);
        initViews();
        btnLogin.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String username = edtxUsername.getText().toString();
                String password = edtxPassword.getText().toString();

                //TODO: Implement authentication with the backend

                //TODO: Implement session management using real data
                sharedPrefs = getActivity().getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putInt(Constants.SHARED_PREFS_UID, 0);
                editor.putString(Constants.SHARED_PREFS_NAME, "Bisma");
                editor.putString(Constants.SHARED_PREFS_LAST_LOGIN, "12/06/2015 3:20PM");
                editor.commit();

                // if login is successful
                loginListener.onLogin();

                break;
        }
    }

    private void initViews() {
        edtxUsername = (EditText) v.findViewById(R.id.username);
        edtxPassword = (EditText) v.findViewById(R.id.password);
        btnLogin = (Button) v.findViewById(R.id.btn_login);
    }

}
