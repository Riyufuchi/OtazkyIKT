package com.example.fdb2;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateUser
{
    private DatabaseReference myRef;
    private long maxID, id;

    public CreateUser()
    {
        myRef = FirebaseDatabase.getInstance().getReference().child("Uzivatele");
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
               maxID = dataSnapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {}
        });
        id = maxID + 1;
        myRef.child("uzivatel"+id).setValue(new User());
    }
}