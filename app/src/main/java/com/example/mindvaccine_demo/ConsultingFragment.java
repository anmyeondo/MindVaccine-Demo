package com.example.mindvaccine_demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultingFragment} factory method to
 * create an instance of this fragment.
 */
public class ConsultingFragment extends Fragment {


    private View view;
    private Button btn_move;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_consult_main,container,false);
        btn_move= rootView.findViewById(R.id.detail_consult);
        btn_move.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),MainActivity2.class);
                startActivityForResult(intent,100);

            }
        });

        return rootView;


    }
}