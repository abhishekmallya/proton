package com.example.proton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    EditText login_email, login_password;
    TextView login_forgot_password;
    Button login_login;
    FirebaseAuth fAuth;
    ProgressDialog mLoadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login_login = findViewById(R.id.login_login);
        login_forgot_password = findViewById(R.id.login_forgot_password);

        login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString();
                String password = login_password.getText().toString();
                fAuth = FirebaseAuth.getInstance();
                mLoadingBar = new ProgressDialog(LoginActivity.this);
                if (email.length() == 0 || password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Please Provide The Required Credentials", Toast.LENGTH_SHORT).show();
                } else {
                    mLoadingBar.setTitle("Logging In");
                    mLoadingBar.setMessage("Please wait while we log you in");
                    mLoadingBar.setCanceledOnTouchOutside(false);
                    mLoadingBar.show();

                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mLoadingBar.dismiss();
                                Intent log_to_home = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(log_to_home);
                                Toast.makeText(LoginActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
                            } else {
                                mLoadingBar.dismiss();
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

            login_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter your email to receive reset link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extracting mail and Sending Reset Link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this, "Reset Link Has Been Sent To Your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Error, Reset Link Couldn't Be Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            //return to login
                    }
                });

                passwordResetDialog.create().show();
            }
        });



    }
}