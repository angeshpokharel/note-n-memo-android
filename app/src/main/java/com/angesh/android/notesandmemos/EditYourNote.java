package com.angesh.android.notesandmemos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;

public class EditYourNote extends AppCompatActivity implements TextWatcher{

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_your_note);

        EditText editText = (EditText) findViewById(R.id.editText);

        Intent i = getIntent();
        noteId = i.getIntExtra("noteId", -1);

        if (noteId != -1) {

            editText.setText(MainActivity.notes.get(noteId));


        }

        editText.addTextChangedListener(this);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        MainActivity.notes.set(noteId, String.valueOf(s));
        MainActivity.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.robpercival.notes", Context.MODE_PRIVATE);

        if (MainActivity.set == null) {

            MainActivity.set = new HashSet<String>();

        } else {

            MainActivity.set.clear();

        }


        MainActivity.set.addAll(MainActivity.notes);
        sharedPreferences.edit().remove("notes").apply();
        sharedPreferences.edit().putStringSet("notes", MainActivity.set).apply();


    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}


