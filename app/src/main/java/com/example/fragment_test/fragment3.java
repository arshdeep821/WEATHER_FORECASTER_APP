package com.example.fragment_test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class fragment3 extends Fragment {

    TextView savedCity;
    Button load_Button;
    String city;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);

//        cityName = getArguments().getString("city");

        savedCity = view.findViewById(R.id.saved_city);
        load_Button = view.findViewById(R.id.load_button);
        //savedCity.setText("Kidda");


        load_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //savedCity.setText(null);
//                ArrayList<String> cityList = getCity_List();
//                for (int i = 0; 0 < cityList.size(); i++) {
//                    savedCity.append(cityList.get(i));
//                }
            }
        });

        return view;
    }


}