package com.example.chatcurso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rvChat;
    private RecyclerView.LayoutManager manager;
    private Adaptador adaptador;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore db;
    private String UID;
    private MaterialButton btnEnviar;
    private TextInputEditText tiMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mUser = mAuth.getCurrentUser();
        adaptador = new Adaptador();

        if(mUser == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            UID = mUser.getUid();
        }

        btnEnviar = findViewById(R.id.btnEnviar);
        tiMensaje = findViewById(R.id.tiMensajeChat);

        rvChat = findViewById(R.id.rvChat);
        manager = new LinearLayoutManager(this);
        rvChat.setLayoutManager(manager);
        rvChat.setAdapter(adaptador);



        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tiMensaje.getText().toString().trim().equals("")){
                    String mensaje = tiMensaje.getText().toString();
                    Date fecha = new Date();
                    Long fechaMilisegundos = fecha.getTime();
                    String nombre = "Alberto";
                    String UID = mUser.getUid();
                    Mensaje mensaje1 = new Mensaje(nombre, mensaje, fechaMilisegundos.toString(), UID);
                    db.collection("Mensajes").add(mensaje1).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Log.d("Mensaje", "Enviado");
                            tiMensaje.setText("");
                        }
                    });
                }
            }
        });

        db.collection("Mensajes").orderBy("fecha", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                List<Mensaje> mensajes = new ArrayList<>();
                for (DocumentChange doc : value.getDocumentChanges()){
                    Mensaje mensaje = doc.getDocument().toObject(Mensaje.class);
                    Date nuevaFecha = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                    String fechaFormateada = format.format(nuevaFecha);
                    mensaje.setFecha(fechaFormateada);
                    adaptador.add(mensaje);
                    rvChat.scrollToPosition(rvChat.getAdapter().getItemCount()-1);
                }
            }
        });
    }
}