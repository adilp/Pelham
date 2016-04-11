package com.adilpatel.pelhamciviccenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
//you will need to import throughout the creation of this code.
//The class needs to extend Fragment


public class fullScheduleFrag extends Fragment {
    //The newInstance() method return the reference to  fragment
    public static fullScheduleFrag newInstance() {
        fullScheduleFrag fragment = new fullScheduleFrag();
        return fragment;
    }
    //MyFragment is the constructor method of our class.
    //this is a java thing. Google it as you this is basic
    //java that you need to know.
    public fullScheduleFrag() {
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
        View rootView = inflater.inflate(R.layout.fragment_full_schedule, container, false);
        //then the btn and the textview
        WebView myWebView = (WebView) rootView.findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        String url = "http://schedules.pelhamciviccomplex.com/allevents/es_default.htm";
        myWebView.loadUrl(url);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });



        return rootView;
    }


} // This is the end. But we still have to change the mainactivity.java to work with it.