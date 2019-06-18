package com.example.a90535.letgo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText ufirstname,ulastname,uemail,upassword,ucpassword,ucontactnum;
    Button btnRegister;
    TextInputLayout userFirstNameWrapper,userLastNameWrapper,userEmailWrapper,userPasswordWwrapper,
            userConfPassWrapper,
            userContactWrapper;
     FirebaseAuth mAuth;
     DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        ufirstname = findViewById(R.id.registerFirstName);
        ulastname = findViewById(R.id.userLastName);
        uemail = findViewById(R.id.regEmailAddress);
        upassword = findViewById(R.id.userPasswordregister);
        ucpassword = findViewById(R.id.confirmPasswordReg);
        ucontactnum = findViewById(R.id.registerContactNo);

        userFirstNameWrapper = findViewById(R.id.userFirstNameWrapper);
        userLastNameWrapper = findViewById(R.id.lastNameWrapper);
        userEmailWrapper = findViewById(R.id.userEmailWrapper);
        userPasswordWwrapper = findViewById(R.id.userPasswordWrapper);
        userConfPassWrapper = findViewById(R.id.confirmPasswordWrapper);
        userContactWrapper = findViewById(R.id.contactNoWrapper);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (mAuth.getCurrentUser() != null) {

                } else {
                    final String firstname = ufirstname.getText().toString().trim();
                    final String lastname = ulastname.getText().toString().trim();
                    final   String email = uemail.getText().toString().trim();
                    final String password = upassword.getText().toString().trim();
                    final   String confpassword = ucpassword.getText().toString().trim();
                    final    String contactno = ucontactnum.getText().toString().trim();







                    if (firstname.isEmpty()) {
                        userFirstNameWrapper.setError("Lütfen Adınızı Girin");
                        userFirstNameWrapper.requestFocus();
                        return;
                    }
                    if (lastname.isEmpty()) {
                        userLastNameWrapper.setError("Lütfen Soy Adınızı Girin");
                        userLastNameWrapper.requestFocus();
                        return;
                    }
                    if (email.isEmpty()) {
                        userEmailWrapper.setError("Lütfen Mail Adresinizi Girin");
                        userEmailWrapper.requestFocus();
                        return;
                    }

                    if (password.isEmpty()) {
                        userPasswordWwrapper.setError("Lütfen Şifrenizi Girin");
                        userPasswordWwrapper.requestFocus();
                        return;
                    }
                    if (confpassword.isEmpty()) {
                        userConfPassWrapper.setError("Lütfen Şifrenizi Onaylayın");
                        userConfPassWrapper.requestFocus();
                        return;
                    }
                    if (!password.equals(confpassword)) {
                        userConfPassWrapper.setError("Girdiğiniz şifre ile eşleşmiyor ");
                        userConfPassWrapper.requestFocus();
                        return;
                    }
                    if (contactno.isEmpty()) {
                        userContactWrapper.setError("Lütfen Telefon Numaranızı Girin");
                        userContactWrapper.requestFocus();
                        return;
                    }
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override


                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                User user=new User(firstname, lastname, email, contactno);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(RegisterActivity.this,"Kullanıcı Başarıyla Oluşturuldu.",Toast.LENGTH_LONG).show();
                                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                            }else{
                                Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });
    }}
