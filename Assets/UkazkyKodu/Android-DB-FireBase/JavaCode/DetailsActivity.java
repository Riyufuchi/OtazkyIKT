package com.example.fdb2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity
{
    private TextView name, role, lastActive, ban;
    private DatabaseReference myRef;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        name = findViewById(R.id.name);
        role = findViewById(R.id.role);
        lastActive = findViewById(R.id.lastActivity);
        ban = findViewById(R.id.ban);
        intent = getIntent();
        myRef = FirebaseDatabase.getInstance().getReference().child("Uzivatele").child(intent.getStringExtra("id"));
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
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

    public void delete(View view)
    {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)
                {
                    case DialogInterface.BUTTON_POSITIVE:
                        myRef.removeValue();
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Chcete smazat záznam?").setPositiveButton("Ano", dialogClickListener).setNegativeButton("Ne", dialogClickListener).show();
    }
}