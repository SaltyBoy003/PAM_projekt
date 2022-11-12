package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList dane1, dane2, dodatek1, cena1;
    CustomAdapter(Context context, ArrayList dane1, ArrayList dane2, ArrayList dodatek1, ArrayList cena1){
        this.context = context;
        this.dane1 = dane1;
        this.dane2 = dane2;
        this.dodatek1 = dodatek1;
        this.cena1 = cena1;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.dane1_txt.setText(String.valueOf(dane1.get(position)));
        holder.dane2_txt.setText(String.valueOf(dane2.get(position)));
        holder.dodatek1_txt.setText(String.valueOf(dodatek1.get(position)));
        holder.cena1_txt.setText(String.valueOf(cena1.get(position)));

    }

    @Override
    public int getItemCount() {
        return dane1.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView dane1_txt, dane2_txt, dodatek1_txt, cena1_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dane1_txt = itemView.findViewById(R.id.dane1);
            dane2_txt = itemView.findViewById(R.id.dane2);
            dodatek1_txt = itemView.findViewById(R.id.cena1);
            cena1_txt = itemView.findViewById(R.id.cena1);

        }
    }
}
