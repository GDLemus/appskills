package com.example.apprecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    FloatingActionButton addFloatingActionButton;

//    Button listarBoton;

    SwitchMaterial switchBtn, checkSwitch;
    usuarioDAO dao = new usuarioDAO();

    List<usuarios> usuariolist;
    usuarioAdapter adapter;

    DatabaseReference databaseReference;
    ValueEventListener eventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        usuariolist = new ArrayList<>();

        adapter = new usuarioAdapter(MainActivity.this, usuariolist);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("usuarios");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuariolist.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()){
                    usuarios usuariosClass = itemSnapshot.getValue(usuarios.class);
                    usuariosClass.setKey(itemSnapshot.getKey());
                    usuariolist.add(usuariosClass);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });







//        listarBoton = findViewById(R.id.listarButton);
//        listarBoton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dao.listar().addOnCompleteListener(task ->
//                {
//                    if (task.isSuccessful()){
//                        List<usuarios> usuariosList = task.getResult();
//                        adapter.setUsuario(usuariosList);
//                    }else {
//                        Toast.makeText(MainActivity.this, "Fallo al listar", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });



        addFloatingActionButton = findViewById(R.id.addFloatingActionButton);
        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, crudActivity.class);
                startActivity(intent);
                finish();
            }
        });





        // Switch themes
        switchBtn = findViewById(R.id.switchBtn);
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    buttonView.setText("Night Mode");
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    buttonView.setText("Light Mode");
                }


            }
        });

        boolean isNigthModeOn = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        switchBtn.setChecked(isNigthModeOn);
        if (isNigthModeOn){
            switchBtn.setText("Night Mode");
        } else {

            switchBtn.setText("Light Mode");
        }


//        checkSwitch = findViewById(R.id.checkSwitch);
//        checkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });

    }
    @Override
    public void recreate() {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        startActivity(getIntent());
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


//    public void listar(){
//        dao.listar().addOnCompleteListener(task ->
//        {
//            if (task.isSuccessful()){
//                List<usuarios> usuariosList = task.getResult();
//                adapter.
//            }else {
//                Toast.makeText(MainActivity.this, "Fallo al listar", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    }
