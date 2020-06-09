package com.amine.hrimech.uv.es;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);


        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");



        TextView txtUserName = (TextView) findViewById(R.id.textView9);
        TextView txtEmail = (TextView) findViewById(R.id.textView14);

        txtUserName.setText(name);
        txtEmail.setText(email);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserPanel.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }
}
