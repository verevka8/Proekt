package com.example.proekt;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyRow> {

    public List<TeaSavedSettings> settingsList;

    public MyAdapter(List<TeaSavedSettings> settingsList) {
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
        holder.bnt.setText(settingsList.get(position).title);
        Log.d("QQQ",holder.bnt.getText().toString());
    }

    @Override
    public int getItemCount() {
        return settingsList.size();
    }

    class MyRow extends RecyclerView.ViewHolder {
        Button bnt;

        public MyRow(@NonNull View itemView) {
            super(itemView);
            bnt = itemView.findViewById(R.id.button3);
            bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Клик", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}