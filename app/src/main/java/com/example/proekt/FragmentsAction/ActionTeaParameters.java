package com.example.proekt.FragmentsAction;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proekt.ListSavedSettings;
import com.example.proekt.TeaSavedSettings;
import com.example.proekt.databinding.FragmentActionTeaParametersBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ActionTeaParameters extends BottomSheetDialogFragment {

    private FragmentActionTeaParametersBinding binding;
    private int position;
    private TeaSavedSettings settings;
    private static ActionTeaParameters instance;

    private ActionTeaParameters() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentActionTeaParametersBinding.inflate(inflater,container,false);
        updateUi(settings);
        binding.changeTeaParameters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeTeaParameters fragment = new ChangeTeaParameters(settings,position);
                fragment.show(getChildFragmentManager(),fragment.getTag());
            }
        });
        binding.makeTea.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ListSavedSettings.getInstance().prepareTea(position);
            }
        });
        return binding.getRoot();
    }

    public static ActionTeaParameters getInstance(){
        Log.d("QQQ",instance == null? "null" : "not null");
        if (instance == null){
            instance = new ActionTeaParameters();
        }
        return instance;
    }
    public void setParams(TeaSavedSettings settings, int position){
        this.settings = settings;
        this.position = position;
    }

    public void updateUi(TeaSavedSettings settings){
        if (settings != null) {
            this.settings = settings;
            binding.nameTeaParameters.setText(settings.title);
            binding.informationTeaParameters.setText("Тип чая: " + Boolean.toString(settings.tea_variety) +
                    "\nКол-во чая: " + Integer.toString(settings.tea_count) +
                    "\nТемпература чая: " + Integer.toString(settings.tea_temperature) +
                    "\nКол-во сахара: " + Integer.toString(settings.sugar_count));
        }
        else{
            dismiss();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }
}