package com.example.apki_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText password_editText;
    String passwordString;
    SharedPreferences sharedPrefs;

    boolean Registered1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        //sharedPrefs.edit().remove("Registered").commit(); //jak chcemy zresetowac shared preferences
        Registered1 = sharedPrefs.getBoolean("Registered", false);
        //System.out.println(sharedPrefs);
        Button RegisterButton = (Button) findViewById(R.id.Register_btn);
        Button Loginbutton = (Button) findViewById(R.id.Login_btn);
        Intent i = new Intent(this,ListaNotatekActivity.class);

        if (!Registered1) {

            Loginbutton.setVisibility(View.GONE); // zmienia widocznosc przycisku
            RegisterButton.setVisibility(View.VISIBLE);

        } else{

            Loginbutton.setVisibility(View.VISIBLE);
            RegisterButton.setVisibility(View.GONE);
        }
        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password_editText = (EditText) findViewById(R.id.Password_input);
                passwordString = password_editText.getText().toString();
                //Toast.makeText(MainActivity.this,Password,Toast.LENGTH_LONG);
                SharedPreferences sp = getApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String password = sp.getString("Password","");

                Boolean xd= password.equals(passwordString);
                if(xd)
                {

                    startActivity(i);
                }
                else
                {
                    Toast toast= Toast.makeText(MainActivity.this,"Podałeś złe hasło!!!",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

        });
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password_editText = (EditText) findViewById(R.id.Password_input);
                passwordString = password_editText.getText().toString();

                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putBoolean("Registered", true);
                editor.putString("Password", passwordString);
                editor.commit();
                Toast.makeText(MainActivity.this,"Dodano nowe hasło",Toast.LENGTH_LONG);

                finish();
                startActivity(getIntent());

            }
        });
    }
}