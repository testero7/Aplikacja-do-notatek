package com.example.apki_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class DodajNotatkeActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageButton notatkaAddButton;
    private EditText tematEditText;
    private EditText notatkaEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_notatke);

        setTitle("Dodaj notatke");
        notatkaAddButton = (ImageButton) findViewById(R.id.add_notatka);
        tematEditText = (EditText) findViewById(R.id.temat_edittext);
        notatkaEditText = (EditText) findViewById(R.id.notatka_edittext);



        dbManager = new DBManager(this);
        dbManager.open();
        notatkaAddButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_notatka:

                final String temat = tematEditText.getText().toString();


                final String notatka = notatkaEditText.getText().toString();

                dbManager.insert(temat, notatka);

                Intent main = new Intent(
                        DodajNotatkeActivity.this,
                        ListaNotatekActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}