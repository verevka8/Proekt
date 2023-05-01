package com.example.proekt.FragmentsAction;

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

import com.example.proekt.ListSavedSettings;
import com.example.proekt.R;
import com.example.proekt.databinding.FragmentAuthorizationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Authorization extends Fragment {

    private FragmentAuthorizationBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public Authorization() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAuthorizationBinding.inflate(inflater,container,false);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

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
        if (!validateForm(email,password)) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(getContext(),"Successful",Toast.LENGTH_SHORT).show();
                ListSavedSettings.getInstance().id = user.getUid();
                Navigation.findNavController(getView()).navigate(R.id.action_authorization_to_mainMenu);
            } else {
                Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signIn(String email, String password) {
        if (!validateForm(email,password)) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(getContext(),"Successful",Toast.LENGTH_SHORT).show();
                ListSavedSettings list = ListSavedSettings.getInstance();
                mDatabase.child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful() && task.getResult().getValue() != null){
                            list.setInstance(task.getResult().getValue(list.getClass()));
                        }
                        else{
                            list.setId(user.getUid());
                        }
                        Navigation.findNavController(getView()).navigate(R.id.action_authorization_to_mainMenu);
                    }
                });
            } else {
                Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateForm(String email,String password) {
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password); // TODO: сделать более умнее
    }
}