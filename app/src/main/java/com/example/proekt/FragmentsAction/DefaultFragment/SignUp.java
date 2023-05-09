package com.example.proekt.FragmentsAction.DefaultFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proekt.ListSavedSettings;
import com.example.proekt.R;
import com.example.proekt.databinding.FragmentSignUpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends Fragment {
    private FragmentSignUpBinding binding;
    private FirebaseAuth mAuth;
    private SharedPreferences pref;

    public SignUp() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater,container,false);
        mAuth = FirebaseAuth.getInstance();
        pref = getActivity().getSharedPreferences("test", Context.MODE_PRIVATE);

        binding.userCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.userEmail.getText().toString();
                String password = binding.userPassword.getText().toString();
                String password_replay = binding.userPasswordReplay.getText().toString();
                String ip = binding.ipAdress.getText().toString();
                if (password.equals(password_replay)){
                    signUp(email,password,ip);
                }
                else{
                    Toast.makeText(getContext(),"Пароли не совпадают.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.imageButtonExitSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).navigate(R.id.action_signUp_to_authorization);
            }
        });
        return binding.getRoot();
    }

    private void signUp(String email, String password,String ip) {
        if (!validateForm(email,password,ip)) {
            Toast.makeText(getContext(), "Неверный формат почты или пароля, или IP-адреса.", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(getContext(),"Успешно!",Toast.LENGTH_SHORT).show();
                ListSavedSettings.getInstance().setId(user.getUid());
                ListSavedSettings.getInstance().setIp(ip);
                ListSavedSettings.getInstance().updateSavedSettings();
                Navigation.findNavController(getView()).navigate(R.id.action_signUp_to_mainMenu);
                saveSession(user.getUid());
            } else {
                Toast.makeText(getContext(), "Регистрация не удалась.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean validateForm(String email,String password,String ip) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && (Patterns.IP_ADDRESS.matcher(ip).matches() || TextUtils.isEmpty(ip)) ;
    }
    private void saveSession(String id){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("id",id);
        editor.apply();
    }
}