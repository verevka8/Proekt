package com.example.proekt.FragmentsAction.BottomSheetFragment;

import android.os.Bundle;

import android.text.BoringLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proekt.FragmentsAction.DefaultFragment.MainMenu;
import com.example.proekt.ListSavedSettings;
import com.example.proekt.TeaSavedSettings;
import com.example.proekt.databinding.FragmentChangeTeaParametersBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ChangeTeaParameters extends BottomSheetDialogFragment {
    private FragmentChangeTeaParametersBinding binding;
    private int position;
    private String nameBeforeChange;

    private TeaSavedSettings settings;

    public ChangeTeaParameters(TeaSavedSettings settings, int position) {
        this.settings = (TeaSavedSettings) settings.clone();
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
        nameBeforeChange = settings.title;
        binding.nameSettings.setText(settings.title);
        binding.variety1.setChecked(settings.tea_variety.equals("Черный"));
        binding.variety2.setChecked(settings.tea_variety.equals("Зеленый"));
        binding.countTea.setText(Double.toString(settings.tea_count) + " г");
        binding.countSugar.setText(Integer.toString(settings.sugar_count) + " шт");
        binding.tempTea.setText(settings.tea_temperature + "°");

        binding.variety1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {settings.setTea_variety("Черный");
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
        binding.closeMenuSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {dismiss();}}
        );
        binding.updateSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ListSavedSettings.getInstance().updateSavedSettings(position,settings,nameBeforeChange.equals(settings.title))){
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