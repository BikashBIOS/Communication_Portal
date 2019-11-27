package com.example.forms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Faculty extends AppCompatActivity {
    EditText txtEmail,txtPassword,txtConfirmPassword,tuname,tname,tphone;
    Button btn_register;
    ProgressBar progressBar;
    RadioButton radioMale,radioFemale;
    String gender="",strcollege="",strbranch="";
    Spinner college,branch;
    String colleges[]={"Select College","CV Raman","GIET","GITA","Silicon","KIIT","IIT,BBSR","NIT,Rourkela","IIIT","Parala Maharaja","Trident","CET","HiTech","IGIT,Saranga","ITER","BPUT","CUTM","Other"};
    String branches[]={"Select Department","Mechanical","Civil","Chemical","ETC","Electrical","CSE","CS & IT","AEI","Mathematics","Physics","Chemistry","Economics","English"};


    DatabaseReference databaseReference;


    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__faculty);

        getSupportActionBar().setTitle("Faculty SignUp");

        tuname=findViewById(R.id.uname);
        tname=findViewById(R.id.name);
        tphone=findViewById(R.id.phone);
        txtEmail=findViewById(R.id.txtEmail);
        txtPassword=findViewById(R.id.txtPassword);
        txtConfirmPassword=findViewById(R.id.txtConfirmPassword);
        btn_register=findViewById(R.id.buttonRegister);
        progressBar=findViewById(R.id.progressbar);
        radioMale=findViewById(R.id.radio_male);
        radioFemale=findViewById(R.id.radio_female);
        college=findViewById(R.id.college);
        branch=findViewById(R.id.branch);

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,colleges);
        college.setAdapter(adapter);
        college.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strcollege=colleges[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_list_item_1,branches);
        branch.setAdapter(adapter1);
        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strbranch=branches[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        firebaseAuth= FirebaseAuth.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference("Faculties");


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name=tname.getText().toString().trim();
                final String uname=tuname.getText().toString().trim();
                final String email=txtEmail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();
                String cpassword=txtConfirmPassword.getText().toString().trim();
                final String phone=tphone.getText().toString().trim();
                final String scollege=strcollege;
                final String sbranch=strbranch;


                if(radioMale.isChecked())
                {
                    gender="Male";
                }
                if (radioFemale.isChecked())
                {
                    gender="Female";
                }

                if (TextUtils.isEmpty(name))
                {
                    Toast.makeText(Signup_Faculty.this, "Please enter your Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(uname))
                {
                    Toast.makeText(Signup_Faculty.this, "Please enter user name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(Signup_Faculty.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(Signup_Faculty.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cpassword))
                {
                    Toast.makeText(Signup_Faculty.this, "Please enter confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length()<6)
                {
                    Toast.makeText(Signup_Faculty.this, "Password should be of 6 or more characters", Toast.LENGTH_SHORT).show();
                }
                if (cpassword.length()<6)
                {
                    Toast.makeText(Signup_Faculty.this, "Confirmed Password less than 6 characters", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);


                if (password.equals(cpassword))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Faculty.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        faculty information=new faculty(name,uname,email,gender,phone,scollege,sbranch);

                                        FirebaseDatabase.getInstance().getReference("Faculties")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                startActivity(new Intent(getApplicationContext(),Login_Faculty.class));
                                                Toast.makeText(Signup_Faculty.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    } else {
                                        Toast.makeText(Signup_Faculty.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
                else
                {
                    Toast.makeText(Signup_Faculty.this, "Confirmed Password not equal to Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
