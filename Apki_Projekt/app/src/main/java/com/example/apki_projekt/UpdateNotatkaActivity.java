package com.example.apki_projekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateNotatkaActivity extends Activity implements View.OnClickListener {

    private EditText temat;
    private Button updateBtn,deleteBtn;
    private EditText notatka;

    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notatka);
        setTitle("Edytuj notatkę");

        dbManager = new DBManager(this);
        dbManager.open();
        temat = (EditText) findViewById(R.id.temat_edittext);
        notatka = (EditText)findViewById(R.id.notatka_edittext);
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button)findViewById(R.id.btn_delete);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String tytul = intent.getStringExtra("temat");
        String tresc = intent.getStringExtra("notatka");
        _id = Long.parseLong(id);
        temat.setText(tytul);
        notatka.setText(tresc);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
//

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_update:
                String title = temat.getText().toString();
                String desc = notatka.getText().toString();
                dbManager.update(_id,title,desc);
                this.returnHome();
                break;
            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;


        }
    }
    public void returnHome(){
        Intent home = new Intent(getApplicationContext(),ListaNotatekActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home);
    }
}