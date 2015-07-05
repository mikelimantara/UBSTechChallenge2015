package com.prototype.ubs.techchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by Michael on 3/7/2015.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private Button btnLogin;
    private ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        imgLogo = (ImageView) findViewById(R.id.img_logo);
        btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            Intent loginIntent = new Intent(this, MainActivity.class);
            startActivity(loginIntent);
        }
    }
}
