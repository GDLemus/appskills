package com.example.apprecycler;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class usuarioDAO {

    private DatabaseReference databaseReference;

    public usuarioDAO(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(usuarios.class.getSimpleName());
    }

    public Task<Void> create(usuarios usuario){
        return databaseReference.push().setValue(usuario);
    }

    public Task<List<usuarios>> listar(){
        final TaskCompletionSource<List<usuarios>> tcs = new TaskCompletionSource<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<usuarios> usuariosList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    usuarios usu = snapshot.getValue(usuarios.class);
                    if (usu != null){
                        usuariosList.add(usu);
                    }
                }
                tcs.setResult(usuariosList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                tcs.setException(error.toException());
            }
        });
    return tcs.getTask();
    }

    public Task<Void> Editar(String key, HashMap<String, Object> hashMap){

        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }
}
