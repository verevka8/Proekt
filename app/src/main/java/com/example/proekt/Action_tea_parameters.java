package com.example.proekt;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.proekt.databinding.FragmentActionTeaParametersBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Action_tea_parameters extends BottomSheetDialogFragment {

    private FragmentActionTeaParametersBinding binding;
    private int position;
    private TeaSavedSettings settings;
    private static Action_tea_parameters instance;

    private Action_tea_parameters() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentActionTeaParametersBinding.inflate(inflater,container,false);
        updateUi(settings);
        binding.changeTeaParameters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Change_tea_parameters fragment = new Change_tea_parameters(settings,position);
                fragment.show(getChildFragmentManager(),fragment.getTag());
            }
        });
        return binding.getRoot();
    }

    public static Action_tea_parameters getInstance(){
        if (instance == null){
            instance = new Action_tea_parameters();
        }
        return instance;
    }
    public void setParams(TeaSavedSettings settings, int position){
        this.settings = settings;
        this.position = position;
    }

    public void updateUi(TeaSavedSettings settings){
        this.settings = settings;
        binding.nameTeaParameters.setText(settings.title);
        binding.informationTeaParameters.setText("Тип чая: " + Boolean.toString(settings.tea_variety) +
                "\nКол-во чая: " + Integer.toString(settings.tea_count) +
                "\nТемпература чая: " + Integer.toString(settings.tea_temperature) +
                "\nКол-во сахара: " + Integer.toString(settings.sugar_count));

    }
}