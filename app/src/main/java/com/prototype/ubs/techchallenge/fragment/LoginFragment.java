package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prototype.ubs.techchallenge.R;

/**
 * Created by Michael on 12/6/2015.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private View v = null;
    private EditText edtxUsername = null;
    private EditText edtxPassword = null;
    private Button btnLogin = null;

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


                break;
        }
    }

    private void initViews() {
        edtxUsername = (EditText) v.findViewById(R.id.username);
        edtxPassword = (EditText) v.findViewById(R.id.password);
        btnLogin = (Button) v.findViewById(R.id.btn_login);
    }

}
