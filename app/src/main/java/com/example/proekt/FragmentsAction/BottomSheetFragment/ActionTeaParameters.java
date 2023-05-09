package com.example.proekt.FragmentsAction.BottomSheetFragment;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
                if (ListSavedSettings.getInstance().prepareTea(position)){
                    dismiss();
                }
                else{
                    Toast.makeText(getContext(),"Вы не ввели IP-адрес чайника",Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.closeActionTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
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
            binding.countTeaAction.setText(Double.toString(settings.tea_count) + " г");
            binding.countSugarAction.setText(Integer.toString(settings.sugar_count) + " шт");
            binding.tempTeaAction.setText(Integer.toString(settings.tea_temperature) + " °");
            binding.varietyTea.setText(settings.tea_variety);
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