package com.example.chatcurso;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private List<Mensaje> mensajes;
    private FirebaseAuth mAuth;

    public Adaptador(){
        mensajes = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
    }

    public void add(Mensaje mensaje){
        mensajes.add(mensaje);
        notifyItemInserted(mensajes.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mensaje, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mensaje mensaje = mensajes.get(position);
        holder.nombre.setText(mensaje.getNombre());
        holder.mensaje.setText(mensaje.getMensaje());
        holder.fecha.setText(mensaje.getFecha().toString());
        if(mAuth.getCurrentUser() != null && mensaje.getUID().equals(mAuth.getCurrentUser().getUid())){
            mensaje.setNombre("Yo");
            holder.nombre.setText(mensaje.getNombre());
            holder.nombre.setGravity(Gravity.END);
            holder.mensaje.setGravity(Gravity.END);
            holder.fecha.setGravity(Gravity.END);
        }
    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private TextView mensaje;
        private TextView fecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombreChat);
            mensaje = itemView.findViewById(R.id.tvMensajeChat);
            fecha = itemView.findViewById(R.id.tvFechaChat);
        }
    }
}
