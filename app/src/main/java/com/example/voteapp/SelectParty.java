package com.example.voteapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectParty extends AppCompatActivity {

    Button Party1,Party2,Party3;
    private DatabaseReference ref;
    private ProgressDialog Bar;
    String Phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_party);

        Party1=(Button)findViewById(R.id.party1);
        Party2=(Button)findViewById(R.id.party2);
        Party3=(Button)findViewById(R.id.party3);
        Intent i=getIntent();
        Phone=i.getStringExtra("phone");
        ref= FirebaseDatabase.getInstance().getReference();
        Bar=new ProgressDialog(this);


        Party1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder=new AlertDialog.Builder(SelectParty.this);
                builder.setTitle("Confirm Your Party");
                builder.setMessage("Do you want to give your vote to Sri Lanka People's Freedom Alliance");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Bar.setTitle("Voting In progress");
                        Bar.setMessage("Please wait..");
                        Bar.setCanceledOnTouchOutside(false);
                        Bar.show();

                        ref.child("Users").child(Phone).child("Party").setValue("Party1").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Intent i=new Intent(SelectParty.this,FinalActivity.class);
                                i.putExtra("phone",Phone);
                                i.putExtra("partyname","Party 1");

                                startActivity(i);

                                Bar.dismiss();
                                Toast.makeText(SelectParty.this, "Your Vote is Submitted to our database..", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                builder.show();




            }
        });
        Party2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(SelectParty.this);
                builder.setTitle("Confirm Your Party");
                builder.setMessage("Do you want to give your vote to Samagi Jana Balawegaya");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Bar.setTitle("Voting In progress");
                        Bar.setMessage("Please wait..");
                        Bar.setCanceledOnTouchOutside(false);
                        Bar.show();

                        ref.child("Users").child(Phone).child("Party").setValue("Party2").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Intent i=new Intent(SelectParty.this,FinalActivity.class);
                                i.putExtra("phone",Phone);
                                i.putExtra("partyname","Party 2");

                                startActivity(i);

                                Bar.dismiss();
                                Toast.makeText(SelectParty.this, "Your Vote is Submitted to our database..", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        Party3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(SelectParty.this);
                builder.setTitle("Confirm Your Party");
                builder.setMessage("Do you want to give your vote to Tamil National Alliance");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Bar.setTitle("Voting In progress");
                        Bar.setMessage("Please wait..");
                        Bar.setCanceledOnTouchOutside(false);
                        Bar.show();

                        ref.child("Users").child(Phone).child("Party").setValue("Party3").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Intent i=new Intent(SelectParty.this,FinalActivity.class);
                                i.putExtra("phone",Phone);
                                i.putExtra("partyname","Party 3");

                                startActivity(i);

                                Bar.dismiss();
                                Toast.makeText(SelectParty.this, "Your Vote is Submitted to our database", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
