package com.example.proekt.FragmentsAction;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proekt.ListSavedSettings;
import com.example.proekt.MyAdapter;
import com.example.proekt.databinding.FragmentMainMenuBinding;


public class MainMenu extends Fragment{
    private FragmentMainMenuBinding binding;
    public static MyAdapter adapter;

    public MainMenu() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainMenuBinding.inflate(inflater,container,false);
        adapter = new MyAdapter(ListSavedSettings.getInstance().settingsList);
        RecyclerView recyclerView = binding.recyclerView;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        binding.button2.setOnClickListener(new View.OnClickListener() { //TODO: переименовать кнопку
            @Override
            public void onClick(View view) {
                TeaParameters fragment = new TeaParameters();
                fragment.show(getChildFragmentManager(),fragment.getTag());
            }
        });
        return binding.getRoot();
    }
}