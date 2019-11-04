package com.example.forms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.SignInButtonImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login_form extends AppCompatActivity {

    EditText temail,tpassword;
    private static final int GOOGLE_SIGN_IN = 123;
    Button login,signup;
    TextView fornew;
    private FirebaseAuth firebaseAuth;
    SignInButtonImpl b_login;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Student LogIn");

        temail=findViewById(R.id.email);
        tpassword=findViewById(R.id.password);
        login=findViewById(R.id.login);
        firebaseAuth=FirebaseAuth.getInstance();
        signup=findViewById(R.id.signup);
        fornew=findViewById(R.id.fornew);
        b_login=findViewById(R.id.google_login);

        b_login.setOnClickListener(view -> signIn());

        mAuthStateListener= firebaseAuth -> {
            if (firebaseAuth.getCurrentUser() != null){
                startActivity(new Intent(Login_form.this,MainActivity.class));
            }
        };
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this, connectionResult -> Toast.makeText(Login_form.this, "Something went wrong", Toast.LENGTH_SHORT).show())
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        login.setOnClickListener(view -> {
            String email=temail.getText().toString().trim();
            String password=tpassword.getText().toString().trim();

            if (TextUtils.isEmpty(email))
            {
                Toast.makeText(Login_form.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password))
            {
                Toast.makeText(Login_form.this, "Please enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                Toast.makeText(Login_form.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login_form.this, "Login Failed ", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Signup_Form.class);
                startActivity(intent);
            }
        });
    }

        private void signIn() {
        Intent signInIntent=Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,GOOGLE_SIGN_IN);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN) {
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                GoogleSignInAccount account=result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
            else {
                Toast.makeText(this, "Authenthication went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential=GoogleAuthProvider.getCredential(account.getIdToken(),null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user=firebaseAuth.getCurrentUser();
                        }
                        else {
                            Toast.makeText(Login_form.this, "Authentication failed !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
