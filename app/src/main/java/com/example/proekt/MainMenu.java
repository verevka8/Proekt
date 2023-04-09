package com.example.proekt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proekt.databinding.FragmentMainMenuBinding;

public class MainMenu extends Fragment {
    private FragmentMainMenuBinding binding;
    public MainMenu() {
        // Required empty public constructor
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
                TeaParameters blankFragment =new TeaParameters();
                blankFragment.show(getChildFragmentManager(),blankFragment.getTag());
            }
        });
        return binding.getRoot();
    }
}