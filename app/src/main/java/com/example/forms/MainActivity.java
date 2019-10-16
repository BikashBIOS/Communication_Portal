package com.example.forms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class MainActivity extends AppCompatActivity {


        private FirebaseAuth firebaseAuth;

        private FirebaseDatabase firebaseDatabase;
        ImageView imageView;
    ;
    FirebaseStorage firebaseStorage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("HOME");

        FloatingActionButton button = findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ImageUpload.class);
                startActivity(intent);
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        StorageReference storageReference=firebaseStorage.getReference("images/").child(firebaseUser.getUid());

        imageView =findViewById(R.id.imageView);

        storageReference.

    }




        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater=getMenuInflater();
            inflater.inflate(R.menu.menu,menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.menuLogout:
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(MainActivity.this,Login_form.class));
                    break;
                case R.id.menuProfile:
                        Intent intent=new Intent(MainActivity.this,Profile.class);
                        startActivity(intent);
                        break;



            }
            return true;
        }






}
