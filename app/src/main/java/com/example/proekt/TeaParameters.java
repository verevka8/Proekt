package com.example.proekt;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proekt.databinding.FragmentTeaParametersBinding;
import com.example.proekt.model.SettingsViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class TeaParameters extends BottomSheetDialogFragment {
    private FragmentTeaParametersBinding binding;

    public TeaParameters() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTeaParametersBinding.inflate(inflater, container, false);
        TeaSavedSettings settings = new TeaSavedSettings();

        binding.variety1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.setTea_variety(true);
            }
        });

        binding.variety2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.setTea_variety(false);
            }
        });

        binding.addPortions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.addTea_count();
                binding.countTea.setText(Integer.toString(settings.getTea_count()));
            }
        });

        binding.removePortions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.removeTea_count();
                binding.countTea.setText(Integer.toString(settings.getTea_count()));
            }
        });

        binding.nameSettings.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                settings.setTitle(editable.toString());
            }
        });

        binding.addSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.addSugar_count();
                binding.countSugar.setText(Integer.toString(settings.getSugar_count()));
            }
        });

        binding.removeSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.removeSugar_count();
                binding.countSugar.setText(Integer.toString(settings.getSugar_count()));
            }
        });

        binding.saveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListSavedSettings list = ListSavedSettings.getInstance();
                list.addSavedSettings(settings);
                dismiss();
            }
        });
        return binding.getRoot();
    }
}