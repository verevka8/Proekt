package com.example.proekt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proekt.databinding.FragmentAuthorizationBinding;

public class Authorization extends Fragment {

    private FragmentAuthorizationBinding binding;

    public Authorization() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       binding = FragmentAuthorizationBinding.inflate(inflater,container,false);
       binding.button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Navigation.findNavController(view).navigate(R.id.action_authorization_to_mainMenu);
           }
       });
       return binding.getRoot();
    }
}