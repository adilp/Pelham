package com.adilpatel.pelhamciviccenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
//you will need to import throughout the creation of this code.
//The class needs to extend Fragment
public class Contact extends Fragment {
    //The newInstance() method return the reference to  fragment
    public static Contact newInstance() {
        Contact fragment = new Contact();
        return fragment;
    }
    //MyFragment is the constructor method of our class.
    //this is a java thing. Google it as you this is basic
    //java that you need to know.
    public Contact() {
    }
    //since we have a button and textview on the xml will use these two
    //variable to connect to them.
    Button button2;
    TextView daveText;
    //this method links the fragment to the layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //connecting the fragment to our layout, new_frag
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        //then the btn and the textview
        button2 = (Button) rootView.findViewById(R.id.button2);
        daveText = (TextView) rootView.findViewById(R.id.textView2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(daveText.getText().toString().contains("Hello Dave")){
                    daveText.setText("I'm sorry Dave I can't do that.");
                }else daveText.setText("Hello Dave");
            }
        });
        // return our View
        return rootView;
    }
} // This is the end. But we still have to change the mainactivity.java to work with it.