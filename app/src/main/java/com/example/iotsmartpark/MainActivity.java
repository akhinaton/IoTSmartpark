package com.example.iotsmartpark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private  DatabaseReference mDatabase;
    private RecyclerView recycle;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<FireModel> list;
    //   RecyclerView recycle;
    Button view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (Button) findViewById(R.id.view);
        recycle = (RecyclerView) findViewById(R.id.recycle);

        final ImageView simpleImageView=(ImageView) findViewById(R.id.articleImage);


        mDatabase=FirebaseDatabase.getInstance().getReference().child("spots");
        mDatabase.keepSynced(true);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = database.child("spots");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<FireModel>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    FireModel value = dataSnapshot1.getValue(FireModel.class);
                    FireModel fire = new FireModel();
                    String name = value.getColor();
                  //  imageModel.setImageId(R.mipmap.ic_star_fill);
                    fire.setColor(name);
                    list.add(fire);
                    Log.d("Hello", "Value is: " + name);

                   }//

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,MainActivity.this);
                RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivity.this,2);
                /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                recycle.setLayoutManager(recyce);
                recycle.setItemAnimator( new DefaultItemAnimator());
                recycle.setAdapter(recyclerAdapter);
            }
        });


    }
}