package com.erasmodev.covidintheworld;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import javax.xml.transform.stream.StreamResult;

public class HomeFragment extends Fragment {

    private static final String STATS_URL = "https://api.covid19api.com/summary";

    Context context;

    ProgressBar progressBar;
    TextView totalCasesTv, newCasesTv, totalDeathsTv, newDeathsTv, totalRecoveredTv, newRecoveredTv;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //init Uis
        progressBar = view.findViewById(R.id.progressBar);
        totalCasesTv = view.findViewById(R.id.totalCasesTv);
        newCasesTv = view.findViewById(R.id.newCasesTv);
        totalDeathsTv = view.findViewById(R.id.totalDeathsTv);
        newDeathsTv = view.findViewById(R.id.newDeathsTv);
        totalRecoveredTv = view.findViewById(R.id.totalRecoveredTv);
        newRecoveredTv = view.findViewById(R.id.newRecoveredTv);

        progressBar.setVisibility(View.GONE);

        loadHomeData();

        return view;
    }

    private void loadHomeData(){
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, STATS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //response received
                handleJsonResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, R.string.some_error, Toast.LENGTH_SHORT).show();
            }
        });
        //add request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private void handleJsonResponse(String response) {
        try {
            //convert Json Object
            JSONObject jsonObject = new JSONObject(response);
            JSONObject globalJson = jsonObject.getJSONObject("Global");

            //get data from json
            String newConfirmed = globalJson.getString("NewConfirmed");
            String totalConfirmed = globalJson.getString("TotalConfirmed");
            String newDeaths = globalJson.getString("NewDeaths");
            String totalDeaths = globalJson.getString("TotalDeaths");
            String newRecovered = globalJson.getString("NewRecovered");
            String totalRecovered = globalJson.getString("TotalRecovered");

            //set data
            totalCasesTv.setText(totalConfirmed);
            newCasesTv.setText(newConfirmed);
            totalDeathsTv.setText(totalDeaths);
            newDeathsTv.setText(newDeaths);
            totalRecoveredTv.setText(totalRecovered);
            newRecoveredTv.setText(newRecovered);


            progressBar.setVisibility(View.GONE);

        } catch (Exception e){
            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadHomeData();
    }
}