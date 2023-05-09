package com.example.proekt.FragmentsAction.DefaultFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proekt.FragmentsAction.BottomSheetFragment.AddIpAdress;
import com.example.proekt.FragmentsAction.BottomSheetFragment.CreateTeaParameters;
import com.example.proekt.ListSavedSettings;
import com.example.proekt.MyAdapter;
import com.example.proekt.R;
import com.example.proekt.databinding.FragmentMainMenuBinding;


public class MainMenu extends Fragment{
    private FragmentMainMenuBinding binding;
    public static MyAdapter adapter;
    private SharedPreferences pref;

    public MainMenu() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainMenuBinding.inflate(inflater,container,false);
        adapter = new MyAdapter(ListSavedSettings.getInstance().settingsList);
        pref = getActivity().getSharedPreferences("test", Context.MODE_PRIVATE);

        RecyclerView recyclerView = binding.recyclerView;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        binding.imageButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).navigate(R.id.action_mainMenu_to_authorization);
                ListSavedSettings.getInstance().setInstance(null);
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("id");
                editor.apply();
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() { //TODO: переименовать кнопку
            @Override
            public void onClick(View view) {
                CreateTeaParameters fragment = new CreateTeaParameters();
                fragment.show(getChildFragmentManager(),fragment.getTag());
            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddIpAdress fragment = new AddIpAdress();
                fragment.show(getChildFragmentManager(),fragment.getTag());
            }
        });
        return binding.getRoot();
    }
}