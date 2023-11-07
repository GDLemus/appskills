package com.example.apprecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class detailActivity extends AppCompatActivity {

    EditText nameEdittext, emailEdittext, passEdittext;

    Button editarBoton, backBoton, eliminarBoton;

    usuarioDAO dao = new usuarioDAO();

    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameEdittext = findViewById(R.id.nameEditTextText);
        emailEdittext = findViewById(R.id.emailEditTextText);


        String nombre = getIntent().getStringExtra("nombre");
        String correo = getIntent().getStringExtra("correo");
        key = getIntent().getStringExtra("key");
        nameEdittext.setText(nombre);
        emailEdittext.setText(correo);

        //Editar
        editarBoton = findViewById(R.id.editarButton);
        editarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nuevoNombre = nameEdittext.getText().toString();
                String nuevoCorreo = emailEdittext.getText().toString();

                if (key != null){
                HashMap hashMap = new HashMap<>();
                hashMap.put("nombre",nuevoNombre);
                hashMap.put("email", nuevoCorreo);
                dao.Editar(key, hashMap).addOnSuccessListener(suc->
                {
                    Toast.makeText(detailActivity.this, "Se modifico correctamente", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->
                {
                    Toast.makeText(detailActivity.this, "Fallo al modificar", Toast.LENGTH_SHORT).show();
                });
            } else {
                    Toast.makeText(detailActivity.this, "No se puede editar sin una clave válida", Toast.LENGTH_SHORT).show();
                }
            }
        });


        eliminarBoton = findViewById(R.id.eliminarButton);
        eliminarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.remove(key).addOnSuccessListener(suc->{
                    Toast.makeText(detailActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(detailActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }).addOnFailureListener(er->
                {
                    Toast.makeText(detailActivity.this, "Fallo al eliminar", Toast.LENGTH_SHORT).show();
                });
            }
        });

        backBoton = findViewById(R.id.backButton);
        backBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}