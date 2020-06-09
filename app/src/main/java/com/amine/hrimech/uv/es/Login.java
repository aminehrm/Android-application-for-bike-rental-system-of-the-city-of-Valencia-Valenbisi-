package com.amine.hrimech.uv.es;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView register = (TextView)findViewById(R.id.lnkRegister);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });

        final EditText txtUserName = (EditText)findViewById(R.id.txtEmail);
        final EditText txtPassword = (EditText)findViewById(R.id.txtPwd);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String username = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();
                try{
                    if(username.length() > 0 && password.length() >0)
                    {
                        String type="logine";
                        BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
                        String kk=backgroundTask.execute(type,username, password).get();
                        String[] data = null;



                        if(kk.equals("login failed")){
                            Toast.makeText(Login.this,"Password or username invalide ", Toast.LENGTH_LONG).show();
                        }else{
                            data  = kk.split(",");
                            Toast.makeText(Login.this,"Successfully Logged In ", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra("name",data[0]);
                            intent.putExtra("type",data[1]);
                            intent.putExtra("email",data[2]);

                            startActivity(intent);
                        }


                    }

                }catch(Exception e)
                {
                    Toast.makeText(Login.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}
