package com.example.johnluu.duan1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HelloActivity extends AppCompatActivity {
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
    }

    public void vaoMainAct(View view){
        i = new Intent(HelloActivity.this,MainActivity.class);
        final ProgressDialog progressDialog = new ProgressDialog(HelloActivity.this,
                R.style.AppTheme_PopupOverlay);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        startActivity(i);
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }
}
