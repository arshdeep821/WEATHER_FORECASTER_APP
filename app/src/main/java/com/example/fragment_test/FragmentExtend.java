package com.example.fragment_test;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentExtend extends Fragment {

    ArrayList<String> city_List = new ArrayList<>();

//    public FragmentExtend() {
//        city_List = new ArrayList<>();
//    }

    public void addCity(String city) {
        city_List.add(city);
    }

    public ArrayList<String> getCity_List() {
        return city_List;
    }


}
