package com.example.chatcurso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private TextInputEditText itCorreo;
    private TextInputEditText itPassword;
    private TextInputEditText itRepetirPassword;
    private Button btnRegistrar;

    private String correo;
    private String password;
    private String password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        asignarVistas();
    }

    private void asignarVistas(){
        itCorreo = findViewById(R.id.itCorreoRegistro);
        itPassword = findViewById(R.id.itPaswordRegistro);
        itRepetirPassword = findViewById(R.id.itRepetirPaswordRegistro);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo = itCorreo.getText().toString();
                password = itPassword.getText().toString();
                password2 = itRepetirPassword.getText().toString();
                if (password.equals(password2)) {
                    if (password.length() >= 6) {
                        crearUsuario();
                    }else{
                        Toast.makeText(getApplicationContext(), "La constraseña es muy corta", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void crearUsuario(){
        mAuth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Intent intent = new Intent(RegisterActivity.this, ChatActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Usuario creado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}