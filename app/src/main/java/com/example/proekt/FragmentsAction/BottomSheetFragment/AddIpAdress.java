package com.example.proekt.FragmentsAction.BottomSheetFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proekt.ListSavedSettings;
import com.example.proekt.databinding.FragmentAddIpAdressBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddIpAdress extends BottomSheetDialogFragment {
    private FragmentAddIpAdressBinding binding;
    public AddIpAdress() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddIpAdressBinding.inflate(inflater,container,false);
        binding.ipAdress.setText(ListSavedSettings.getInstance().ip.replace("-","."));
        binding.connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip = binding.ipAdress.getText().toString();
                if (Patterns.IP_ADDRESS.matcher(ip).matches()){
                    ListSavedSettings.getInstance().setIp(ip);
                    ListSavedSettings.getInstance().updateSavedSettings();
                    dismiss();
                }
                else {
                    Toast.makeText(getContext(), "Неверный формат IP-адреса.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.closeAddIpAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return binding.getRoot();
    }
}