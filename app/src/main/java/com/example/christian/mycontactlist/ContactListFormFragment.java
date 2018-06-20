package com.example.christian.mycontactlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A fragment representing a list of Items.
 * interface.
 */
public class ContactListFormFragment extends Fragment {
    private static final String STATE_FIRSTNAME = "First Name";
    private static final String STATE_LASTNAME = "Last Name";
    private static final String STATE_ADDRESS_1 = "ADDR01";
    private static final String STATE_ADDRESS_2 = "ADDR02";
    private static final String STATE_CITY = "City";
    private static final String STATE_STATE = "STATE";
    private static final String STATE_ZIP = "ZIP";
    private static final String STATE_HOME = "HOME";
    private static final String STATE_CELL = "CELL";
    private static final String STATE_EMAIL = "EMAIL";
    private static final String STATE_BIRTHDAY = "BIRTHDAY";

    private EditText mFirstName, mLastName, mAddr1, mAddr2, mCity, mState, mZip, mHome, mCell, mEmail, mBirthday;


    private String FirstName = "";
    private String LastName = "";
    private String Addr1 = "";
    private String Addr2 = "";
    private String City = "";
    private String State = "";
    private String Zip = "";
    private String Home = "";
    private String Cell = "";
    private String Email = "";
    private String Birthday = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.contact_list_form_fragment, container, false);
        if(savedInstanceState != null){
            FirstName = savedInstanceState.getString("STATE_FIRSTNAME", "");
            LastName = savedInstanceState.getString("STATE_LASTNAME", "");
            Addr1 = savedInstanceState.getString("STATE_ADDRESS_1", "");
            Addr2 = savedInstanceState.getString("STATE_ADDRESS_2", "");
            City = savedInstanceState.getString("STATE_CITY", "");
            State = savedInstanceState.getString("STATE_STATE", "");
            Zip = savedInstanceState.getString("STATE_ZIP", "");
            Home = savedInstanceState.getString("STATE_HOME", "");
            Cell = savedInstanceState.getString("STATE_CELL", "");
            Email = savedInstanceState.getString("STATE_EMAIL", "");
            Birthday = savedInstanceState.getString("STATE_BIRTHDAY", "");

        }

        mFirstName = (EditText) view.findViewById(R.id.editTextFirstName);
        mFirstName.setText(FirstName);
        mLastName = (EditText) view.findViewById(R.id.editTextLastName);
        mLastName.setText(LastName);
        mAddr1 = (EditText) view.findViewById(R.id.editTextAddr);
        mAddr1.setText(Addr1);
        mAddr2= (EditText) view.findViewById(R.id.editTextAddr2);
        mAddr2.setText(Addr2);
        mCity = (EditText) view.findViewById(R.id.editTextCity);
        mCity.setText(City);
        mState = (EditText) view.findViewById(R.id.editTextState);
        mState.setText(State);
        mZip = (EditText) view.findViewById(R.id.editTextZip);
        mZip.setText(Zip);
        mHome = (EditText) view.findViewById(R.id.editTextHomePhone);
        mHome.setText(Home);
        mCell = (EditText) view.findViewById(R.id.editTextCellPhone);
        mCell.setText(Cell);
        mEmail = (EditText) view.findViewById(R.id.editTextEmail);
        mEmail.setText(Email);
        mBirthday = (EditText) view.findViewById(R.id.editTextBirthday);
        mBirthday.setText(Birthday);
        return view;
    }
    public void setVars(){
        FirstName = mFirstName.getText().toString();
        LastName = mLastName.getText().toString();
        Addr1 = mAddr1.getText().toString();
        Addr2 = mAddr2.getText().toString();
        City = mCity.getText().toString();
        State = mState.getText().toString();
        Zip = mZip.getText().toString();
        Home = mHome.getText().toString();
        Cell = mCell.getText().toString();
        Email = mEmail.getText().toString();
        Birthday = mBirthday.getText().toString();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        setVars();
    }

    @Override
    public void onPause() {
        super.onPause();
        setVars();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
        outState.putString(STATE_FIRSTNAME, FirstName);
        outState.putString(STATE_LASTNAME, LastName);
        outState.putString(STATE_ADDRESS_1, Addr1);
        outState.putString(STATE_ADDRESS_2, Addr2);
        outState.putString(STATE_CITY, City);
        outState.putString(STATE_STATE, State);
        outState.putString(STATE_ZIP, Zip);
        outState.putString(STATE_HOME, Home);
        outState.putString(STATE_CELL, Cell);
        outState.putString(STATE_EMAIL, Email);
        outState.putString(STATE_BIRTHDAY, Birthday);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            FirstName = savedInstanceState.getString("STATE_FIRSTNAME", "");
            LastName = savedInstanceState.getString("STATE_LASTNAME", "");
            Addr1 = savedInstanceState.getString("STATE_ADDRESS_1", "");
            Addr2 = savedInstanceState.getString("STATE_ADDRESS_2", "");
            City = savedInstanceState.getString("STATE_CITY", "");
            State = savedInstanceState.getString("STATE_STATE", "");
            Zip = savedInstanceState.getString("STATE_ZIP", "");
            Home = savedInstanceState.getString("STATE_HOME", "");
            Cell = savedInstanceState.getString("STATE_CELL", "");
            Email = savedInstanceState.getString("STATE_EMAIL", "");
            Birthday = savedInstanceState.getString("STATE_BIRTHDAY", "");

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            FirstName = savedInstanceState.getString("STATE_FIRSTNAME", "");
            LastName = savedInstanceState.getString("STATE_LASTNAME", "");
            Addr1 = savedInstanceState.getString("STATE_ADDRESS_1", "");
            Addr2 = savedInstanceState.getString("STATE_ADDRESS_2", "");
            City = savedInstanceState.getString("STATE_CITY", "");
            State = savedInstanceState.getString("STATE_STATE", "");
            Zip = savedInstanceState.getString("STATE_ZIP", "");
            Home = savedInstanceState.getString("STATE_HOME", "");
            Cell = savedInstanceState.getString("STATE_CELL", "");
            Email = savedInstanceState.getString("STATE_EMAIL", "");
            Birthday = savedInstanceState.getString("STATE_BIRTHDAY", "");
        }
    }
}
