package com.example.forms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Clubhead extends AppCompatActivity {
    EditText temail,tpassword;
    Button login,signup;
    TextView fornew;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__clubhead);
        getSupportActionBar().setTitle("Club Head LogIn");

        temail=findViewById(R.id.email);
        tpassword=findViewById(R.id.password);
        login=findViewById(R.id.login);
        firebaseAuth= FirebaseAuth.getInstance();
        signup=findViewById(R.id.signup);
        fornew=findViewById(R.id.fornew);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=temail.getText().toString().trim();
                String password=tpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(Login_Clubhead.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(Login_Clubhead.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_Clubhead.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    Toast.makeText(Login_Clubhead.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Login_Clubhead.this, "Login Failed ", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Signup_Clubhead.class);
                startActivity(intent);
            }
        });
    }


}

