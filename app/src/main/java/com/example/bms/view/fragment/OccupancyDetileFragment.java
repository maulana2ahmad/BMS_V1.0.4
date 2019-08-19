package com.example.bms.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bms.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class OccupancyDetileFragment extends Fragment {


    public OccupancyDetileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_occupancy_detile, container, false);

        return view;
    }

}
