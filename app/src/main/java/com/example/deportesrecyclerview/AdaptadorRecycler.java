package com.example.deportesrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorRecycler
        extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolder> {

    List<Deporte> misfilas;
    private OnRecyclerViewLongItemClickListener itemLongClickListener;

    public void setOnItemLongClickListener
            (OnRecyclerViewLongItemClickListener listener){
        itemLongClickListener = listener;
    }

    public AdaptadorRecycler(List<Deporte> datosEnviados){
        misfilas = datosEnviados;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.
                        from(parent.getContext()).
                        inflate(R.layout.fila,parent,false);
        final ViewHolder viewHolder = new ViewHolder(v);
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemLongClickListener!=null){
                    itemLongClickListener.onItemLongClick(
                            v,viewHolder.getAdapterPosition());;
                }
                return true;
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int pos = position;
        holder.miTexto.setText(misfilas.get(position).getTexto());
        holder.miCheck.setChecked(misfilas.get(position).isCheckbox());
        holder.miImagen.setImageResource(misfilas.get(position).getImagen());
        holder.miCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(v.getContext(),
                        misfilas.get(pos).getTexto(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return misfilas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView miImagen;
        TextView miTexto;
        CheckBox miCheck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            miImagen = itemView.findViewById(R.id.imageViewLinea);
            miTexto = itemView.findViewById(R.id.textViewLinea);
            miCheck = itemView.findViewById(R.id.checkBoxLinea);
        }
    }
}
