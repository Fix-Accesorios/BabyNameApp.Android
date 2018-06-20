package com.example.christian.mycontactlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;

import org.w3c.dom.Text;

/**
 * A fragment representing a list of Items.
 * interface.
 */
public class ContactListAddFragment extends Fragment {
    public Button backBtn;
    private static final String TAG_FORM_FRAGMENT = "formFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_list_add_fragment, container, false);
        initButtonClick(view);
        initForm();
        return view;
    }

    private void initForm(){
        ContactListFormFragment contact_form = new ContactListFormFragment();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_contact_add_container, contact_form, TAG_FORM_FRAGMENT).commit();
    }

    private View.OnClickListener backBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            backBtnClicked(v);
        }
    };

    private void initButtonClick(View v){
        backBtn = (Button) v.findViewById(R.id.backButton);
        backBtn.setOnClickListener(backBtnClickListener);
    }

    private void backBtnClicked(View v) {
        ContactListItemFragment first = new ContactListItemFragment();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, first, TAG_FORM_FRAGMENT).commit();
    }
}
