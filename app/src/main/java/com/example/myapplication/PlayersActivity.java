package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
    }

    public void getNames(View v){
        EditText player1btn=findViewById(R.id.Player1Name);
        EditText player2btn=findViewById(R.id.Player2Name);

        String player1=player1btn.getText().toString();
        String player2=player2btn.getText().toString();

        Intent i = new Intent(this, GameActivity.class);

        i.putExtra("player1Name", player1);
        i.putExtra("player2Name", player2);
        startActivity(i);

    }
}