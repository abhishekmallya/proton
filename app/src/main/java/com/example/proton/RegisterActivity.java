package com.example.proton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class RegisterActivity extends AppCompatActivity {

/*----------------------------------- Variables & Assignment -----------------------------------*/
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    Button register_button;
    EditText register_username, register_contact, register_email, register_password, register_address,register_confirm_password;
    int flag;
/*------------------------------------ main ----------------------------------------------*/
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*-----------------------------------Firebase---------------------------*/
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(RegisterActivity.this);

        /*-----------------------------------Assignments---------------------------------*/
        register_username = findViewById(R.id.register_username);
        register_contact = findViewById(R.id.register_contact);
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register_address = findViewById(R.id.register_address);
        register_confirm_password = findViewById(R.id.register_confirm_password);
        register_button = findViewById(R.id.register_register);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*----------------register_... changed to username------------*/
                String username = register_username.getText().toString();
                int contact = Integer.valueOf(register_contact.getText().toString());
                String email = register_email.getText().toString();
                String password = register_password.getText().toString();
                String confirm_password = register_confirm_password.getText().toString();
                String address = register_address.getText().toString();
                if (!password.equals(confirm_password)) {
                    Toast.makeText(RegisterActivity.this, "Passwords Do not Match", Toast.LENGTH_SHORT).show();
                } else if (username.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Enter a Username", Toast.LENGTH_SHORT).show();
                } else if (email.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Enter your email", Toast.LENGTH_SHORT).show();
                } else if (password.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Password field cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    mLoadingBar.setTitle("Status");
                    mLoadingBar.setMessage("Working On Your Registration Request");
                    mLoadingBar.setCanceledOnTouchOutside(false);
                    mLoadingBar.show();

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mLoadingBar.dismiss();
                                Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                Intent success = new Intent(RegisterActivity.this, LoginActivity.class);
                                success.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(success);
                            } else {
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                mLoadingBar.dismiss();
                            }
                        }
                    });
                }
            }
        });
    }
}