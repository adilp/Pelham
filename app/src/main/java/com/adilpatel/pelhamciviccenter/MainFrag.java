package com.adilpatel.pelhamciviccenter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.adilpatel.pelhamciviccenter.app.AppController;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainFrag extends Fragment {

    //private WeakReference<ProcessJSON> asyncTaskWeakRef;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";



    private static String TAG = MainActivity.class.getSimpleName();
    // json object response url
    private String urlJsonObj = "http://10.0.2.2:3000/api/schedule/hockey/pickup";


    // json array response url
    private String urlJsonArryPickup = "http://10.0.2.2:3000/api/schedule/hockey/pickup";
    private String urlJsonArryStickPuck = "http://10.0.2.2:3000/api/schedule/hockey/stickpuck";
    private ProgressDialog pDialog;

    private TextView pickupDateLabel;
    private TextView pickupTimeLabel;
    private TextView pickupSpotsLabel;
    private TextView stickPuckDateLabel;
    private TextView stickPuckTimeLabel;
    private TextView stickPuckSpotsLabel;

    private String jsonTime;
    private String jsonDate;
    private String jsonSpots;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MainFrag newInstance() {
        MainFrag fragment = new MainFrag();
        return fragment;
    }
    //MyFragment is the constructor method of our class.
    //this is a java thing. Google it as you this is basic
    //java that you need to know.
    public MainFrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        pickupDateLabel = (TextView) rootView.findViewById(R.id.mPickupDate);
        pickupTimeLabel = (TextView)rootView.findViewById(R.id.mPickupTime);
        pickupSpotsLabel = (TextView)rootView.findViewById(R.id.mPickupSpots);
        stickPuckDateLabel = (TextView)rootView.findViewById(R.id.mStickPuckDate);
        stickPuckTimeLabel = (TextView)rootView.findViewById(R.id.mStickPuckTime);
        stickPuckSpotsLabel = (TextView)rootView.findViewById(R.id.mStickPuckSpots);

//        pDialog = new ProgressDialog(getActivity());
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.setIndeterminate(true);
//        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        pDialog.show();



        makeJsonArrayRequest(urlJsonArryPickup);
        makeJsonArrayRequest(urlJsonArryStickPuck);

        CardView pickupCard;

        pickupCard = (CardView) rootView.findViewById(R.id.mPickupCard);

        pickupCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "this is my Toast message!!! =)",
                        Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

//    private void makeJsonObjectRequest() {
//
//
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
//                urlJsonObj, null, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d(TAG, response.toString());
//                Toast.makeText(getActivity(), "msg msg", Toast.LENGTH_LONG).show();
//
//
//                try {
//                    // Parsing json object response
//                    // response will be a json object
//                    String date = response.getString("date");
//                    String time = response.getString("time");
//
//                    //JSONObject phone = response.getJSONObject("phone");
//                   // String home = phone.getString("home");
//                   // String mobile = phone.getString("mobile");
//
//                    jsonResponse = "";
//                    jsonResponse += date + "\n\n";
//                    jsonResponse += time + "\n\n";
////                    jsonResponse += "Home: " + home + "\n\n";
////                    jsonResponse += "Mobile: " + mobile + "\n\n";
//
//                    pickupDateLabel.setText(jsonResponse);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getActivity(),
//                            "Error: " + e.getMessage(),
//                            Toast.LENGTH_LONG).show();
//                }
//                // pDialog.dismiss();;
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                Toast.makeText(getActivity(),
//                        error.getMessage(), Toast.LENGTH_LONG).show();
//                // hide the progress dialog
//                //pDialog.dismiss();
//            }
//        });
//
//        // Adding request to request queue
//        AppController.getInstance().addToRequestQueue(jsonObjReq);
//    }

    /**
     * Method to make json array request where response starts with [
     * */
    private void makeJsonArrayRequest(final String urlJsonArry) {

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonTime = "";
                            jsonDate = "";
                            jsonSpots = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String time = person.getString("time");
                                String date = person.getString("date");
                                String spots = person.getString("spots");
//                                JSONObject phone = person
//                                        .getJSONObject("phone");
//                                String home = phone.getString("home");
//                                String mobile = phone.getString("mobile");

                                jsonDate +=  date;
                                jsonTime += time;
                                jsonSpots += spots;
//                                jsonResponse += "Home: " + home + "\n\n";
//                                jsonResponse += "Mobile: " + mobile + "\n\n\n";

                            }


                            if (urlJsonArry == "http://10.0.2.2:3000/api/schedule/hockey/pickup"){

                                pickupTimeLabel.setText(jsonTime);
                                 pickupDateLabel.setText(jsonDate);
                                 pickupSpotsLabel.setText(jsonSpots);
                            }

                            if (urlJsonArry == "http://10.0.2.2:3000/api/schedule/hockey/stickpuck"){
                                stickPuckTimeLabel.setText(jsonTime);
                                stickPuckDateLabel.setText(jsonDate);
                                stickPuckSpotsLabel.setText(jsonSpots);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }
}






