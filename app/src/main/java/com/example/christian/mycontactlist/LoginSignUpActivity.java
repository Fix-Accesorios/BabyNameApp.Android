package com.example.christian.mycontactlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class LoginSignUpActivity extends AppCompatActivity {
    private Context ctx;
    private FrameLayout mFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup_activity_layout);
        Intent intent = getIntent();
        ctx = this.getApplicationContext();
        String type = intent.getStringExtra("FormType");
        mFrameLayout =  findViewById(R.id.auth_fragment_container);
        SetFragmentView(type);
    }
    static void makeToast(Context _ctx, String s) {
        Toast.makeText(_ctx, s, Toast.LENGTH_SHORT).show();
    }
    private void SetFragmentView(String type){
        if(type.isEmpty()){
            makeToast(ctx, "No type given");
        }
        if(type.equalsIgnoreCase("signUp")){
            SignUpFormFragment frag1 = new SignUpFormFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.auth_fragment_container, frag1).commit();
        }
        else {
            LoginFormFragment frag2 = new LoginFormFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.auth_fragment_container, frag2).commit();
        }
    }
}