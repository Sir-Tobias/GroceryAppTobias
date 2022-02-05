package com.example.greetings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String key1 = "USER_NAME";

    //public static final String LIST_TAG = "LIST";

    public void login(View v) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();

        if (!(usernameString.length() <= 6)) {
            Intent i = new Intent(this, Welcome_Screen.class);
            i.putExtra("USER_NAME", usernameString);
            startActivity(i);

        }else if (!(passwordString.trim().length() <= 6)) {
            Intent i = new Intent(this, Welcome_Screen.class);
            i.putExtra("USER_NAME", usernameString);
            startActivity(i);

        }else
            Toast.makeText(MainActivity.this, "Your username or password is too short please try again", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_main));
    }

}

