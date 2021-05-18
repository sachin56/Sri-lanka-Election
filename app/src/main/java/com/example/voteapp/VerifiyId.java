package com.example.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VerifiyId extends AppCompatActivity {

    String Phone;
    EditText Id;
    Button Idbutton;
    private DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifiy_age);

        Intent i=getIntent();
        Phone=i.getStringExtra("phone");

        Id=(EditText) findViewById(R.id.idproof);
        Idbutton=(Button)findViewById(R.id.verifyagebutton);
        ref= FirebaseDatabase.getInstance().getReference();

        Idbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(Id.getText().toString()))
                {
                    Toast.makeText(VerifiyId.this, "Please Enter you id..", Toast.LENGTH_LONG).show();
                }
                else
                {
                    ref.child("Users").child(Phone).child("ID").setValue(Id.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    Intent i=new Intent(VerifiyId.this,SelectParty.class);
                                    i.putExtra("phone",Phone);
                                    startActivity(i);
                                }
                            });
                }
            }
        });

    }
}