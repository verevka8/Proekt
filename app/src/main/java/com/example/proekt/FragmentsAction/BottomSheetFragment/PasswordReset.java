package com.example.proekt.FragmentsAction.BottomSheetFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proekt.R;
import com.example.proekt.databinding.FragmentPasswordResetBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends BottomSheetDialogFragment {
    private FragmentPasswordResetBinding binding;
    private FirebaseAuth mAuth;

    public PasswordReset() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPasswordResetBinding.inflate(inflater,container,false);
        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("ru");
        binding.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.userEmail.getText().toString();
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getContext(),"Письмо для сброса пароля успешно отправлено.",Toast.LENGTH_SHORT).show();
                                dismiss();
                            }
                            else{
                                Toast.makeText(getContext(),"Отправить письмо не удалось, попробуйте снова через некоторое время.",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getContext(), "Неправильный формат почты или пароля.", Toast.LENGTH_SHORT).show();                }
            }
        });
        binding.closePasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return binding.getRoot();
    }
}