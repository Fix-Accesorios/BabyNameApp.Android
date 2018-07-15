package com.example.christian.mycontactlist;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class SignUpFormFragment extends Fragment {
    RequestQueue queue;
    String url = "https://murmuring-lake-56352.herokuapp.com/users";
    //String url = "http://172.18.47.49:8080/users";
    private Button submitBtn;
    private EditText fName;
    private TextView fNameLabel;
    private EditText lName;
    private TextView lNameLabel;
    private EditText Email;
    private TextView EmailLabel;
    private EditText ConEmail;
    private TextView ConEmailLabel;
    private EditText Password;
    private TextView PasswordLabel;
    private EditText ConPassword;
    private TextView ConPasswordLabel;
    private View v;

    private StringRequest RegisterRequest() {
        return new StringRequest(Request.Method.POST, url,
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
                            makeToast("Could not parse JSON");
                        }
                        try {
                            token = json.getString("token");
                        } catch (org.json.JSONException e) {
                            Error = true;
                            makeToast("Could not parse JSON Object for token");
                        }
                        writeApiToken(token, Error);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        makeToast("Cannot create account. Try Again.");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("firstname", GetFirstName());
                params.put("lastname", GetLastName());
                params.put("email", GetEmail());
                params.put("password", GetPassword());
                return params;
            }
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_form_fragment, container, false);
        v = view;
        queue = Volley.newRequestQueue(v.getContext());
        GetElements(view);
        SetSubmitClick();
        return view;
    }

    private void GetElements(View view) {
        submitBtn = (Button) view.findViewById(R.id.sign_up_submit_button);
        fName = (EditText) view.findViewById(R.id.first_name_edit);
        lName = (EditText) view.findViewById(R.id.last_name_edit);
        Email = (EditText) view.findViewById(R.id.email_edit);
        ConEmail = (EditText) view.findViewById(R.id.email_confirm_edit);
        Password = (EditText) view.findViewById(R.id.password_edit);
        ConPassword = (EditText) view.findViewById(R.id.password_confirm_edit);
        fNameLabel = (TextView) view.findViewById(R.id.first_name_label);
        lNameLabel = (TextView) view.findViewById(R.id.last_name_label);
        EmailLabel = (TextView) view.findViewById(R.id.email_label);
        ConEmailLabel = (TextView) view.findViewById(R.id.email_confirm_label);
        PasswordLabel = (TextView) view.findViewById(R.id.password_label);
        ConPasswordLabel = (TextView) view.findViewById(R.id.password_confirm_label);

    }

    private void makeToast(String s) {
        Toast.makeText(v.getContext(), s, Toast.LENGTH_SHORT).show();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private boolean StringsEqual(String str_1, String str_2) {
        if (str_1.isEmpty()) {
            return false;
        }
        if (str_2.isEmpty()) {
            return false;
        }
        return str_1.equals(str_2);
    }

    private String GetFirstName() {
        return fName.getText().toString();
    }

    private String GetLastName() {
        return lName.getText().toString();
    }

    private String GetEmail() {
        return Email.getText().toString();
    }

    private String GetConEmail() {
        return ConEmail.getText().toString();
    }

    private String GetPassword() {
        return Password.getText().toString();
    }

    private String GetConPassword() {
        return ConPassword.getText().toString();
    }

    private void GoToActivity() {
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        startActivity(intent);
    }

    protected void writeApiToken(String fileContents, boolean Error) {
        String filename = "api_token.txt";
        FileOutputStream outputStream;
        if (!Error) {
            try {
                outputStream = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(fileContents.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            GoToActivity();
        } else {
            makeToast("Cannot create account. Try Again.");
        }
    }

    private boolean ValidateData() {
        String FirstName = GetFirstName();
        String LastName = GetLastName();
        String Email = GetEmail();
        String ConEmail = GetConEmail();
        String Password = GetPassword();
        String ConPassword = GetConPassword();
        boolean MatchingEmails = StringsEqual(Email, ConEmail);
        boolean MatchingPasswords = StringsEqual(Password, ConPassword);
        boolean validFirstName = !FirstName.isEmpty();
        boolean validLastName = !LastName.isEmpty();

        if (!validFirstName) {
            fNameLabel.setTextColor(getResources().getColor(R.color.colorTextDanger));
        } else {
            fNameLabel.setTextColor(getResources().getColor(R.color.colorTextInfo));
        }
        if (!validLastName) {
            lNameLabel.setTextColor(getResources().getColor(R.color.colorTextDanger));
        } else {
            lNameLabel.setTextColor(getResources().getColor(R.color.colorTextInfo));
        }
        if (!MatchingEmails) {
            EmailLabel.setTextColor(getResources().getColor(R.color.colorTextDanger));
            ConEmailLabel.setTextColor(getResources().getColor(R.color.colorTextDanger));
        } else {
            EmailLabel.setTextColor(getResources().getColor(R.color.colorTextInfo));
            ConEmailLabel.setTextColor(getResources().getColor(R.color.colorTextInfo));
        }
        if (!MatchingPasswords) {
            PasswordLabel.setTextColor(getResources().getColor(R.color.colorTextDanger));
            ConPasswordLabel.setTextColor(getResources().getColor(R.color.colorTextDanger));
        } else {
            PasswordLabel.setTextColor(getResources().getColor(R.color.colorTextInfo));
            ConPasswordLabel.setTextColor(getResources().getColor(R.color.colorTextInfo));
        }

        return (MatchingEmails && MatchingPasswords && validFirstName && validLastName);
    }

    private void SetSubmitClick() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isNetworkConnected()) {
                    boolean isValid = ValidateData();
                    if (isValid) {
                        makeToast("Valid");
                        queue.add(RegisterRequest());
                    } else {
                        makeToast("Not Valid");
                    }
                }else{
                    makeToast("No Internet Access");
                }
            }
        });
    }
}
