package com.example.chatcurso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private Button button;
    private Button btnInicioSesion;
    private TextInputEditText tiCorreo;
    private TextInputEditText tiPass;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInicioSesion = findViewById(R.id.btnInicioSesion);
        tiCorreo = findViewById(R.id.tiCorreoInicioSesion);
        tiPass = findViewById(R.id.tiPassInicioSesion);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        if (mUser != null){
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        }
        Log.i("Saludo", "Hola mundo desde android");

        button = findViewById(R.id.btnRegistro);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        eventoInicioSesion();
    }

    private void eventoInicioSesion(){
        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = tiCorreo.getText().toString().trim();
                String password = tiPass.getText().toString();
                mAuth.signInWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

}