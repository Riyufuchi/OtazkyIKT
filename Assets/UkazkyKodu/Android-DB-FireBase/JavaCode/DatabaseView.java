package com.example.fdb2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class DatabaseView extends AppCompatActivity
{
    private DatabaseReference myRef;
    private List<String> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_view);
        ListView ls = findViewById(R.id.list);
        datalist = new ArrayList<String>();
        myRef = FirebaseDatabase.getInstance().getReference("Uzivatele");
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datalist);
        ls.setAdapter(aa);

        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                int i = 0;
                datalist.clear();
                for(DataSnapshot childSnapShot : dataSnapshot.getChildren())
                {
                    i++;
                    datalist.add("uzivatel" + i);
                }
                aa.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id)
            {
                String item = (String)adapter.getItemAtPosition(position);

                Intent intent = new Intent(DatabaseView.this, DetailsActivity.class);
                intent.putExtra("id",item);
                startActivity(intent);
            }
        });
    }
}