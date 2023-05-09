package com.example.proekt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proekt.FragmentsAction.BottomSheetFragment.ActionTeaParameters;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyRow> {

    public ArrayList<TeaSavedSettings> settingsList;

    public MyAdapter(ArrayList<TeaSavedSettings> settingsList) {
        this.settingsList = settingsList;
    }

    @NonNull
    @Override
    public MyRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_settings, parent, false);
        return new MyRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRow holder, int position) {
        holder.btn.setText(settingsList.get(position).title);
    }

    @Override
    public int getItemCount() {
        return settingsList.size();
    }

    class MyRow extends RecyclerView.ViewHolder {
        Button btn;

        public MyRow(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.item_button);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActionTeaParameters fragment = ActionTeaParameters.getInstance();
                    fragment.setParams(settingsList.get(getAdapterPosition()),getAdapterPosition());
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    fragment.show(activity.getSupportFragmentManager(),fragment.getTag());
                }
            });
        }
    }

}