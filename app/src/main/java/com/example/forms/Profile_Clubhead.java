package com.example.forms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_Clubhead extends AppCompatActivity {
    TextView profilename1,profileuname,profileemail,profilegender,profilephone,profilecollege,profilebranch,profileyear;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__clubhead);

        profilename1=findViewById(R.id.profilename);
        profileuname=findViewById(R.id.username);
        profileemail=findViewById(R.id.email);
        profilegender=findViewById(R.id.gender);
        profilecollege=findViewById(R.id.college);
        profilebranch=findViewById(R.id.branch);
        profilephone=findViewById(R.id.phone);
        profileyear=findViewById(R.id.year);

        firebaseAuth=FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference databaseReference=firebaseDatabase.getReference("Club Heads").child(firebaseUser.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clubhead information1=dataSnapshot.getValue(clubhead.class);
                profilename1.setText("Name- "+information1.getName());
                profileuname.setText("User Name- "+information1.getUname());
                profileemail.setText("Email- "+information1.getEmail());
                profilegender.setText("Email- "+information1.getGender());
                profilephone.setText("Phone- "+information1.getPhone());
                profilecollege.setText("College- "+information1.getCollege());
                profilebranch.setText("Branch- "+information1.getBranch());
                profileyear.setText("Year- "+information1.getYear());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile_Clubhead.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
