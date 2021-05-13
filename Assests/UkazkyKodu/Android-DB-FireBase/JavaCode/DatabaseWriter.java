package com.example.fdb2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseWriter extends AppCompatActivity
{
    private TextView name, role, lastActive, ban;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_writer);
        name = findViewById(R.id.name);
        role = findViewById(R.id.role);
        lastActive = findViewById(R.id.lastActivity);
        ban = findViewById(R.id.ban);
        myRef = FirebaseDatabase.getInstance().getReference().child("Uzivatele");
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                long ID = dataSnapshot.getChildrenCount();
                myRef = FirebaseDatabase.getInstance().getReference().child("Uzivatele").child(String.valueOf("uzivatel" + (ID+1)));
                if(dataSnapshot.child("name").getValue() != null && dataSnapshot.child("role").getValue() != null && dataSnapshot.child("naposledAktivni").getValue() != null && dataSnapshot.child("ban").getValue() != null)
                {
                    name.setText(dataSnapshot.child("name").getValue().toString());
                    role.setText(dataSnapshot.child("role").getValue().toString());
                    lastActive.setText(dataSnapshot.child("naposledAktivni").getValue().toString());
                    ban.setText(dataSnapshot.child("ban").getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {}
        });
    }

    public void updateDB(View v)
    {
        User uziv = new  User();
        uziv.setName(name.getText().toString());
        uziv.setRole(role.getText().toString());
        uziv.setNaposledAktivni(lastActive.getText().toString());
        uziv.setBan(ban.getText().toString());
        myRef.setValue(uziv);
    }
}