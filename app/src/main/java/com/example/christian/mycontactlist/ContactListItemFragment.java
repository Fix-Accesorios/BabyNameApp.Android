package com.example.christian.mycontactlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A fragment representing a list of Items.
 * interface.
 */
public class ContactListItemFragment extends Fragment {

    private static final String TAG_ADD_FRAGMENT = "addFragment";
    public Button addBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_list_item_fragment, container, false);
        initButtonClick(view);
        return view;
    }
    private View.OnClickListener addBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addBtnClicked(v);
        }
    };
    private void initButtonClick(View v){
        addBtn = (Button) v.findViewById(R.id.addContactButton);
        addBtn.setOnClickListener(addBtnClickListener);
    }

    private void addBtnClicked(View v) {
        ContactListAddFragment contact_add = new ContactListAddFragment();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, contact_add, TAG_ADD_FRAGMENT).commit();
    }
}
