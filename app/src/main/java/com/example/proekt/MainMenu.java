package com.example.proekt;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proekt.databinding.FragmentAuthorizationBinding;
import com.example.proekt.databinding.FragmentMainMenuBinding;
import com.example.proekt.databinding.FragmentTeaParametersBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainMenu extends Fragment{
    private FragmentMainMenuBinding binding;

    public MainMenu() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainMenuBinding.inflate(inflater,container,false);



        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeaParameters Fragmen = new TeaParameters();
                Fragmen.show(getChildFragmentManager(),Fragmen.getTag());
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAdapter adapter = new MyAdapter(ListSavedSettings.getInstance().settingsList);
                RecyclerView recyclerView = binding.recyclerView;
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }
        });

        return binding.getRoot();
    }
}