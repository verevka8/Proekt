package com.example.proekt.FragmentsAction.BottomSheetFragment;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proekt.FragmentsAction.DefaultFragment.MainMenu;
import com.example.proekt.ListSavedSettings;
import com.example.proekt.TeaSavedSettings;
import com.example.proekt.databinding.FragmentCreateTeaParametersBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class CreateTeaParameters extends BottomSheetDialogFragment {
    private FragmentCreateTeaParametersBinding binding;

    public CreateTeaParameters() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTeaParametersBinding.inflate(inflater, container, false);
        TeaSavedSettings settings = new TeaSavedSettings();

        binding.variety1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.setTea_variety("Черный");
            }
        });

        binding.variety2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.setTea_variety("Зеленый");
            }
        });

        binding.addPortions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.addTea_count();
                binding.countTea.setText(Double.toString(settings.tea_count) + " г");
            }
        });

        binding.removePortions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.removeTea_count();
                binding.countTea.setText(Double.toString(settings.tea_count) + " г");
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
                binding.countSugar.setText(Integer.toString(settings.sugar_count) + " шт");
            }
        });

        binding.removeSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.removeSugar_count();
                binding.countSugar.setText(Integer.toString(settings.sugar_count) + " шт");
            }
        });
        binding.addTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.addTeaTemp();
                binding.tempTea.setText(Integer.toString(settings.tea_temperature) + "°");
            }
        });
        binding.removeTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.removeTeaTemp();
                binding.tempTea.setText(Integer.toString(settings.tea_temperature) + "°");
            }
        });

        binding.saveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListSavedSettings list = ListSavedSettings.getInstance();
                if (list.addSavedSettings(settings)) {
                    dismiss();
                    MainMenu.adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getContext(), "Настройка с таким названием уже существует!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.closeMenuSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return binding.getRoot();
    }
}