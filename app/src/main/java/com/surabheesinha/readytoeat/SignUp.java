package com.surabheesinha.readytoeat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.surabheesinha.readytoeat.Model.User;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtphone,edtname,edtpassword;
    Button btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtname =(MaterialEditText)findViewById(R.id.edtname);
        edtphone =(MaterialEditText)findViewById(R.id.edtphone);
        edtpassword=(MaterialEditText)findViewById(R.id.edtpassword);

        btnSignup = (Button)findViewById(R.id.btnSignUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                      //Check if already user exist with phone number
                        if(dataSnapshot.child(edtphone.getText().toString()).exists()){
                            Toast.makeText(SignUp.this,"Phone Number already registered",Toast.LENGTH_LONG).show();

                        }
                        else{
                            User user = new User(edtname.getText().toString(),edtpassword.getText().toString());
                            table_user.child(edtphone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this,"Sign Up successfully",Toast.LENGTH_LONG).show();
                            finish();

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
