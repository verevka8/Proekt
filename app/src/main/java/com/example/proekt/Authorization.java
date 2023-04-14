package com.example.proekt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.proekt.databinding.FragmentAuthorizationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authorization extends Fragment {

    private FragmentAuthorizationBinding binding;
    private FirebaseAuth mAuth;

    public Authorization() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        binding = FragmentAuthorizationBinding.inflate(inflater,container,false);
        binding.userSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.userEmail.getText().toString();
                String password = binding.userPassword.getText().toString();
                signIn(email, password);
            }
        });

        binding.userSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.userEmail.getText().toString();
                String password = binding.userPassword.getText().toString();
                signUp(email, password);
            }
        });
       return binding.getRoot();
    }

    private void signUp(String email, String password) {
        Log.d("QQQ", "createAccount:" + email);
        if (!validateForm(email,password)) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), task -> {
            if (task.isSuccessful()) {
                Log.d("QQQ", "createUserWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(getContext(),"Successful",Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigate(R.id.action_authorization_to_mainMenu);
            } else {
                Log.w("QQQ", "createUserWithEmail:failure", task.getException());
                Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signIn(String email, String password) {
        Log.d("QQQ", "signIn:" + email);
        if (!validateForm(email,password)) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), task -> {
            if (task.isSuccessful()) {
                Log.d("QQQ", "signInWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(getContext(),"Successful",Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigate(R.id.action_authorization_to_mainMenu);
            } else {
                Log.w("QQQ", "signInWithEmail:failure", task.getException());
                Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateForm(String email,String password) {
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password);
    }

}