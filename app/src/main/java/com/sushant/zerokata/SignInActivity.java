package com.sushant.zerokata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";
    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;
    ProgressDialog progressDialog;
   FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference root= db.getReference().getRoot();
    DatabaseReference child;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            //user already logged in
            //TODO:add the case when user already logged in
            Toast.makeText(SignInActivity.this, "Already in :)", Toast.LENGTH_SHORT).show();
            onSignInCompletion(firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid());
        }

        emailEditText= (EditText) findViewById(R.id.et_email);
        passwordEditText= (EditText) findViewById(R.id.et_password);
        loginButton= (Button) findViewById(R.id.bt_login);

        progressDialog=new ProgressDialog(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });


    }

    void registerUser(){
        final String email=emailEditText.getText().toString();
        final String password=passwordEditText.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Logging in .......");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(SignInActivity.this, "logged in", Toast.LENGTH_SHORT).show();
                    onSignInCompletion(firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid());
                }else{
                    progressDialog.setMessage("Registering new User ....");
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> taskRegister) {
                            progressDialog.dismiss();
                            if(taskRegister.isSuccessful()){
                                Toast.makeText(SignInActivity.this, "Registered new user", Toast.LENGTH_SHORT).show();

                                Map<String,Object> map=new HashMap<>();
                                map.put(firebaseAuth.getCurrentUser().getUid(),"");
                                root.updateChildren(map);

                                onSignInCompletion(firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid());
                            }
                            else{
                                Toast.makeText(SignInActivity.this, "Sign in Failure", Toast.LENGTH_SHORT).show();
                                //Log.d(TAG, "onComplete: failure "+taskRegister.getResult().toString());
                            }
                        }
                    });
                }
            }
        });
    }

    void onSignInCompletion(String mail,String uId){
////        root= db.getReference().getRoot();
//        //firebaseAuth.signOut();
//        Map<String,Object> map=new HashMap<>();
//        map.put(mail,"");
//        root.updateChildren(map);
        Map<String,Object> map=new HashMap<>();
        map.put(uId,"");
        root.updateChildren(map);
        child=root.child(uId);
        PlayerPojo playerPojo=new PlayerPojo(mail,1,0);
        Map<String,Object> map2=playerPojo.toMap();

//        Map<String,Object> map2=new HashMap<>();
//        map2.put("email",mail);
//      map2.put("online",1);
        child.updateChildren(map2);

        Intent intent=new Intent(this,OnlinePlayersActivity.class);
        intent.putExtra("email",mail);
        intent.putExtra("uId",uId);
        startActivity(intent);

    }
}
