package com.example.christian.mycontactlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.lang.Object;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;



public class LoginFormFragment extends Fragment {
    private Button submitBtn;
    private EditText passwordEdit;
    private EditText emailEdit;
    private TextView emailLabel;
    private TextView passwordLabel;
    RequestQueue queue;
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    private StringRequest LogIn(){
        String url = "https://murmuring-lake-56352.herokuapp.com/login";
        //String url = "http://172.18.47.49:8080/login";
        Uri.Builder uri_b = new Uri.Builder();
        uri_b.appendQueryParameter("email", GetEmail());
        uri_b.appendQueryParameter("password", GetPassword());
        String n_uri = url + uri_b.build().toString();
        return new StringRequest(Request.Method.GET, n_uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        boolean Error = false;
                        // response
                        String res = response;
                        JSONObject json = new JSONObject();
                        String token = "";
                        try {
                            json = new JSONObject(response);
                        } catch (org.json.JSONException e) {
                            Error = true;
                            Log.d("Error", "Could not parse JSON");
                        }
                        try {
                            token = json.getString("token");
                        } catch (org.json.JSONException e) {
                            Error = true;
                            Log.d("Error", "Could not parse JSON Object for token");
                        }
                        writeApiToken(token, Error);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        makeToast(getView().getContext(), "Try Again.");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", GetEmail());
                params.put("password", GetPassword());
                return params;
            }


        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_form_fragment, container, false);
        GetElements(view);
        queue = Volley.newRequestQueue(view.getContext());
        SetEvents();
        return view;
    }

    private void GetElements(View view) {
        submitBtn = (Button) view.findViewById(R.id.submit_button);
        passwordEdit = (EditText) view.findViewById(R.id.password_edit_Text);
        emailEdit = (EditText) view.findViewById(R.id.email_edit_Text);
        emailLabel = (TextView) view.findViewById(R.id.email_label);
        passwordLabel = (TextView) view.findViewById(R.id.password_label);
    }

    private void SetEvents() {
        SetSubmitEvent();
    }

    private String GetEmail() {
        return emailEdit.getText().toString();
    }

    private String GetPassword() {
        return passwordEdit.getText().toString();
    }

    private boolean ValidateData() {
        String email = GetEmail();
        String password = GetPassword();
        boolean goodEmail = !email.isEmpty();
        boolean goodPassword = !password.isEmpty();
        if (goodPassword) {
            passwordLabel.setTextColor(getResources().getColor(R.color.colorTextInfo));
        } else {
            passwordLabel.setTextColor(getResources().getColor(R.color.colorTextDanger));
        }
        if (goodEmail) {
            emailLabel.setTextColor(getResources().getColor(R.color.colorTextInfo));
        } else {
            emailLabel.setTextColor(getResources().getColor(R.color.colorTextDanger));
        }
        return (goodEmail && goodPassword);
    }
    private void GoToActivity(){
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        startActivity(intent);
    }
    protected void writeApiToken(String fileContents, boolean Error) {
        String filename = "api_token.txt";
        FileOutputStream outputStream;
        if(!Error) {
            try {
                outputStream = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(fileContents.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            GoToActivity();
        }else{
            makeToast(getView().getContext(), "Cannot create account. Try Again.");
        }
    }
    private void SetSubmitEvent() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isNetworkConnected()){
                    boolean isValid = ValidateData();

                    if(isValid){
                        queue.add(LogIn());
                    }else{
                        makeToast(getView().getContext(), "Email and Password needed");
                    }
                }else{
                    makeToast(getView().getContext(), "No Internet Access");
                }
            }
        });
    }
    static void makeToast(Context _ctx, String s) {
        Toast.makeText(_ctx, s, Toast.LENGTH_SHORT).show();
    }
}
