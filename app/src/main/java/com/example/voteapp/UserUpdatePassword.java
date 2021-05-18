package com.example.voteapp;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserUpdatePassword extends AppCompatActivity {

    EditText UpdatePassword,ConfirmPassword;
    Button UserBtn;
    ProgressDialog Bar;

    String password1,password2;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_password);

        Bar=new ProgressDialog(this);
        Intent i=getIntent();
        phone=i.getStringExtra("phone");

        UpdatePassword=(EditText)findViewById(R.id.updatepass1);
        ConfirmPassword=(EditText)findViewById(R.id.updatepass2);
        UserBtn=(Button)findViewById(R.id.userupdatepasswordbtn);

        UserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password1=UpdatePassword.getText().toString();
                password2=ConfirmPassword.getText().toString();

                if(TextUtils.isEmpty(password1))
                {
                    Toast.makeText(UserUpdatePassword.this, "Please enter password...", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password2))
                {
                    Toast.makeText(UserUpdatePassword.this, "Please confirm password...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(password1.equals(password2))
                    {
                        Bar.setTitle("Update Password");
                        Bar.setMessage("Please wait...");
                        Bar.setCanceledOnTouchOutside(false);
                        Bar.show();

                        ChangePassword(password1,password2);
                    }
                    else
                    {
                        Toast.makeText(UserUpdatePassword.this, "Please check your password ", Toast.LENGTH_SHORT).show();

                    }


                }


            }
        });
    }

    private void ChangePassword(final String pass1, String pass2) {

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                RootRef.child("Users").child(phone).child("Password").setValue(pass1)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Bar.dismiss();
                                    Toast.makeText(UserUpdatePassword.this, "Password update successful", Toast.LENGTH_SHORT).show();

                                    Intent i=new Intent(UserUpdatePassword.this,welcomeActivity.class);
                                    startActivity(i);
                                }
                            }
                        });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

