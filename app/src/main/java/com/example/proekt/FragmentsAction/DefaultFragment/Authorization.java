package com.example.proekt.FragmentsAction.DefaultFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proekt.FragmentsAction.BottomSheetFragment.PasswordReset;
import com.example.proekt.ListSavedSettings;
import com.example.proekt.R;
import com.example.proekt.databinding.FragmentAuthorizationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Authorization extends Fragment {
    private FragmentAuthorizationBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private SharedPreferences pref;

    public Authorization() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAuthorizationBinding.inflate(inflater,container,false);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        pref = getActivity().getSharedPreferences("test", Context.MODE_PRIVATE);
        String id = pref.getString("id",null);
        if (id != null){
            downloadSettings(id);
        }
        binding.userSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(binding.userEmail.getText().toString(), binding.userPassword.getText().toString());
            }
        });
        binding.userSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).navigate(R.id.action_authorization_to_signUp);
            }
        });
        binding.resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordReset fragment = new PasswordReset();
                fragment.show(getChildFragmentManager(),fragment.getTag());
            }
        });
       return binding.getRoot();
    }

    private void signIn(String email, String password) {
        if (!validateForm(email,password)) {
            Toast.makeText(getContext(), "Неправильный формат почты или пароля.", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(getContext(),"Успешно!",Toast.LENGTH_SHORT).show();
                downloadSettings(user.getUid());
                saveSession(user.getUid());
            } else {
                Toast.makeText(getContext(), "Неправильная почта или пароль.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean validateForm(String email,String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            return false;
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void saveSession(String id){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("id",id);
        editor.apply();
    }
    private void downloadSettings(String id){
        ListSavedSettings list = ListSavedSettings.getInstance();
        mDatabase.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful() && task.getResult().getValue() != null){
                    list.setInstance(task.getResult().getValue(list.getClass()));
                }
                else{
                    list.setId(id);
                }
                Navigation.findNavController(getView()).navigate(R.id.action_authorization_to_mainMenu);
            }
        });
    }
}