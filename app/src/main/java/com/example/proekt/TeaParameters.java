package com.example.proekt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proekt.databinding.FragmentTeaParametersBinding;
import com.example.proekt.model.SettingsViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class TeaParameters extends BottomSheetDialogFragment {
    private FragmentTeaParametersBinding binding;
    private SettingsViewModel model;

    public TeaParameters() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTeaParametersBinding.inflate(inflater, container, false);
        model = new ViewModelProvider(getActivity()).get(SettingsViewModel.class);
        binding.setViewModel(model);
        binding.addPortions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.addTea_count();
                binding.countTea.setText(model.getTea_count().toString());
            }
        });
        binding.removePortions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.removeTea_count();
                binding.countTea.setText(model.getTea_count().toString());
            }
        });
        binding.closeMenuSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return binding.getRoot();
    }
}