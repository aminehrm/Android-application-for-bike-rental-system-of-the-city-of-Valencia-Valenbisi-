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

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        TextView login = (TextView)findViewById(R.id.lnkLogin);
        login.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
            }
        });

        final EditText txtUserName = (EditText)findViewById(R.id.txtName);
        final EditText txtPassword = (EditText)findViewById(R.id.txtPwd);
        final EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        final EditText txtRpPassword = (EditText)findViewById(R.id.rptxtPwd);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String username = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();
                String rppassword = txtRpPassword.getText().toString();
                String email = txtEmail.getText().toString();

                try{

                    if(username.length() > 0 && password.length() >0 && password.length()>0 && rppassword.length()>0)
                    {
                        if(password.equals(rppassword))   {
                            BackgroundTask backgroundTaske= new BackgroundTask(getApplicationContext());
                            String test=backgroundTaske.execute("existuser",username,email).get();
                            Log.d("sol",test);
                            if(test.equals("notexist")){
                                String typee="register";
                                BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
                                backgroundTask.execute(typee,username,email, password);
                                Toast.makeText(Registration.this,"Successfully Registred", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Registration.this, Login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Registration.this,"This user already exist !", Toast.LENGTH_LONG).show();
                            }


                        }else{
                            Toast.makeText(Registration.this,"Password not match", Toast.LENGTH_LONG).show();
                        }

                        /*if(password.equals(rppassword))   {
                            DBHelper dbUser = new DBHelper(Registration.this);

                        if(dbUser.ExistUser(email, username))
                        {
                            Toast.makeText(Registration.this,"This user already exist !", Toast.LENGTH_LONG).show();
                        }else{

                            dbUser.insertUser(username,email,password,type);
                            Toast.makeText(Registration.this,"Successfully Registred", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Registration.this, Login.class);

                            startActivity(intent);
                            dbUser.close();
                        }
                        }else{
                            Toast.makeText(Registration.this,"Password not match", Toast.LENGTH_LONG).show();
                        }*/
                    }

                }catch(Exception e)
                {
                    Toast.makeText(Registration.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}
