package com.example.forms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {
    Button student,clubhead,faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        getSupportActionBar().setTitle("Select Who You Are");

        student=findViewById(R.id.student);

        clubhead=findViewById(R.id.clubhead);
        faculty=findViewById(R.id.faculty);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login_form.class);
                startActivity(intent);
            }
        });

        clubhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login_Clubhead.class);
                startActivity(intent);
            }
        });

        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login_Faculty.class);
                startActivity(intent);
            }
        });
    }
    /*private void func(){
        startActivity(new Intent(MainPage.this,Profile.class));
    }*/
}
