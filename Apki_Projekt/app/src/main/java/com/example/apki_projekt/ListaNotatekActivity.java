package com.example.apki_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListaNotatekActivity extends AppCompatActivity {

        private DBManager dbManager;

        private ListView listView;

        private SimpleCursorAdapter adapter;

        final String[] baza = new String[] { DBHelper._ID,
                DBHelper.TEMAT, DBHelper.NOTATKA };

        final int[] polaBaza = new int[] {
                R.id.id,
                R.id.temat,
                R.id.notatka };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.fragment_list);

            dbManager = new DBManager(this);
            dbManager.open();
            Cursor cursor = dbManager.fetch();

            listView = (ListView) findViewById(R.id.list_view);
            listView.setEmptyView(findViewById(R.id.empty));

            adapter = new SimpleCursorAdapter(this,
                    R.layout.activity_view_notatka,
                    cursor,
                    baza,
                    polaBaza,
                    0);

            adapter.notifyDataSetChanged();

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                    TextView idTextView = view.findViewById(R.id.id);
                    TextView titleTextView = (TextView) view.findViewById(R.id.temat);
                    TextView descTextView = (TextView) view.findViewById(R.id.notatka);

                    String id = idTextView.getText().toString();
                    String temat = titleTextView.getText().toString();
                    String notatka = descTextView.getText().toString();

                    Intent modify_intent = new Intent(getApplicationContext(), UpdateNotatkaActivity.class);
                    modify_intent.putExtra(
                            "temat",
                            temat);
                    modify_intent.putExtra(
                            "notatka",
                            notatka);
                    modify_intent.putExtra(
                            "id",
                            id);

                    startActivity(modify_intent);
                }
            });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();
            if (id == R.id.add_notatka) {

                Intent add_note = new Intent(this, DodajNotatkeActivity.class);
                startActivity(add_note);

            }
            if (id == R.id.add_noti) {

                Intent add_noti = new Intent(this, DodajNotiActivity.class);
                startActivity(add_noti);

            }
            return super.onOptionsItemSelected(item);
        }

    }