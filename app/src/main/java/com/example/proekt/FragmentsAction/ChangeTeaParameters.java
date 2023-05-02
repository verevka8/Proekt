package com.example.proekt.FragmentsAction;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proekt.ListSavedSettings;
import com.example.proekt.TeaSavedSettings;
import com.example.proekt.databinding.FragmentChangeTeaParametersBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ChangeTeaParameters extends BottomSheetDialogFragment {
    private FragmentChangeTeaParametersBinding binding;
    private int position;
    private TeaSavedSettings settings;

    public ChangeTeaParameters(TeaSavedSettings settings, int position) {
        this.settings = settings;
        this.position = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChangeTeaParametersBinding.inflate(inflater,container,false);
        binding.nameSettings.setText(settings.title);
        binding.variety1.setChecked(settings.tea_variety);
        binding.variety2.setChecked(!settings.tea_variety);
        binding.countTea.setText(Integer.toString(settings.tea_count));
        binding.countSugar.setText(Integer.toString(settings.sugar_count));
        // Добавить температуру

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
                binding.countTea.setText(Integer.toString(settings.tea_count));
            }
        });

        binding.removePortions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.removeTea_count();
                binding.countTea.setText(Integer.toString(settings.tea_count));
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
                binding.countSugar.setText(Integer.toString(settings.sugar_count));
            }
        });

        binding.removeSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.removeSugar_count();
                binding.countSugar.setText(Integer.toString(settings.sugar_count));
            }
        });
        binding.closeMenuSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {dismiss();}}
        );
        binding.updateSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ListSavedSettings.getInstance().updateSavedSettings(position,settings)){
                    MainMenu.adapter.notifyDataSetChanged();
                    ActionTeaParameters.getInstance().updateUi(settings);
                    dismiss();
                }
                else {
                    Toast.makeText(getContext(), "Настройка с таким названием уже существует!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.removeSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ListSavedSettings.getInstance().removeSavedSettings(position);
              dismiss();
              MainMenu.adapter.notifyDataSetChanged();
              ActionTeaParameters.getInstance().updateUi(null);

            }
        });
        return binding.getRoot();
    }
}