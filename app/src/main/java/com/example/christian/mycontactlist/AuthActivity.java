package com.example.christian.mycontactlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AuthActivity extends AppCompatActivity {
    private Button loginBtn;
    private Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_screen);
        Intent intent = getIntent();
        loginBtn = findViewById(R.id.log_in_button);
        signUpBtn = findViewById(R.id.sign_up_button);
        setClickEvents();
    }
    static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }
    private void setClickEvents(){
        setLogInClick();
        setSignUpClick();
    }
    private void setSignUpClick(){
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                makeToast(AuthActivity.this.getApplicationContext(), "Load Sign Up Activity");
            }
        });
    }
    private void setLogInClick(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                makeToast(AuthActivity.this.getApplicationContext(), "Load Log In Activity");
            }
    });
    }
}
