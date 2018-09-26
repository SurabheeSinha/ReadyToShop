package com.surabheesinha.readytoeat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.surabheesinha.readytoeat.Model.User;

public class SignIn extends AppCompatActivity {

    EditText edtphone,edtname,edtpassword;
    Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Intent intent = getIntent();


        edtpassword = (MaterialEditText)findViewById(R.id.edtpassword);
        edtphone = (MaterialEditText)findViewById(R.id.edtphone);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        //Init Firbase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            ProgressDialog mDlog = new ProgressDialog(SignIn.this);
            //mDlog.setMessage("Loading...");
            //mDlog.show();


            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(edtphone.getText().toString()).exists()){


                        //User information from Firebase
                        //created a model class for it

                        User user = dataSnapshot.child(edtphone.getText().toString()).getValue(User.class);
                        if(user.getPassword().equals(edtpassword.getText().toString())){
                            Toast.makeText(SignIn.this,"Sign In Successfull!", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(SignIn.this,"Sign In Failed !!!", Toast.LENGTH_LONG).show();
                        }
                        }
                        else{
                            Toast.makeText(SignIn.this,"User doesn't exist",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });



    }
}
