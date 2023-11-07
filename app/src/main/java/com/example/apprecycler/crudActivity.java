package com.example.apprecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class crudActivity extends AppCompatActivity {

    EditText nameEdittext, emailEdittext, passEdittext;

    Button insertarBoton, backBoton;

    usuarioDAO dao = new usuarioDAO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        nameEdittext = findViewById(R.id.nameEditTextText);
        emailEdittext = findViewById(R.id.emailEditTextText);
        passEdittext = findViewById(R.id.passEditTextText);

        insertarBoton = findViewById(R.id.insertaButton);
        insertarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuarios usu = new usuarios(nameEdittext.getText().toString(), emailEdittext.getText().toString(), passEdittext.getText().toString());
                dao.create(usu).addOnSuccessListener(suc->{
                    Toast.makeText(crudActivity.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->
                {
                    Toast.makeText(crudActivity.this, "Fallo al insertar", Toast.LENGTH_SHORT).show();
                });

            }
        });

        backBoton = findViewById(R.id.backButton);
        backBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(crudActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}