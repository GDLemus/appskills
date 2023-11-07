package com.example.apprecycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class usuarioAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<usuarios> usuariolist;

    //Agregar context para hacerlo clickeable
    private Context context;


    public usuarioAdapter(Context context, List<usuarios> usuariolist) {
        this.context = context;
        this.usuariolist = usuariolist;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nombreView.setText(usuariolist.get(position).getNombre());
        holder.correoView.setText(usuariolist.get(position).getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detailActivity.class);
                intent.putExtra("nombre", usuariolist.get(holder.getAdapterPosition()).getNombre());
                intent.putExtra("correo", usuariolist.get(holder.getAdapterPosition()).getEmail());
                intent.putExtra("key", usuariolist.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usuariolist.size();
    }
}
    class MyViewHolder extends RecyclerView.ViewHolder{

         TextView nombreView;
         TextView correoView;
         CardView redCard;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            nombreView = itemView.findViewById(R.id.nombreTextView);
            correoView = itemView.findViewById(R.id.correoTextView);
            redCard = itemView.findViewById(R.id.recCard);
        }

    }

