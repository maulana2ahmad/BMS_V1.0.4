package com.example.bms.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bms.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OccupancyByTVFragment extends Fragment {


    public OccupancyByTVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_occupancy_by_tv, container, false);
    }

}
