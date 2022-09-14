package tw.edu.pu.s1082827.mfgt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class init extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtEmail;
    private Button btnSubmit;
    private TextView txtLoginInfo;

    private boolean isSigningUp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init2);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);

        btnSubmit = findViewById(R.id.edtSubmit);

        txtLoginInfo = findViewById(R.id.textLoginInfo);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(init.this,FriendsActivity.class));
            finish();
        }

        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(edtEmail.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()){
                    if(isSigningUp && edtUsername.getText().toString().isEmpty()){
                        Toast.makeText(init.this, "Invalid input", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(isSigningUp){
                    handleSignUp();
                }else{
                    handleLogin();
                }
            }
        });

        txtLoginInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isSigningUp){
                    isSigningUp = false;
                    edtUsername.setVisibility(View.GONE);
                    btnSubmit.setText("Log in");
                    txtLoginInfo.setText("Don't have an account? Sign up");
                }else{
                    isSigningUp = true;
                    edtUsername.setVisibility(View.VISIBLE);
                    btnSubmit.setText("Sign up");
                    txtLoginInfo.setText("Already have an account? Log in");
                }
            }
        });
    }

    private void handleSignUp(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference("user/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new User(edtUsername.getText().toString(),edtEmail.getText().toString(),""));
                    startActivity(new Intent(init.this,FriendsActivity.class));
                    Toast.makeText(init.this,"Signed Up successfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(init.this, task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handleLogin(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(init.this,FriendsActivity.class));
                    Toast.makeText(init.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(init.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    }