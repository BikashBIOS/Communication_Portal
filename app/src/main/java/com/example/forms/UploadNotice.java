package com.example.forms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UploadNotice extends AppCompatActivity {
    Button notice,pdf;
    TextView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notice);
        getSupportActionBar().setTitle("What You Want To Do");

        notice=findViewById(R.id.upNotice);
        pdf=findViewById(R.id.upPdf);
        home=findViewById(R.id.homedir);

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UploadNotice.this,ImageUpload.class));
            }
        });

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UploadNotice.this,PdfUpload.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UploadNotice.this,MainActivity.class));
            }
        });
    }
}
