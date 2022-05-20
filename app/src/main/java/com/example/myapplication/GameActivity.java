package com.example.myapplication;

import static com.example.myapplication.R.id;
import static com.example.myapplication.R.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R.drawable;

import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    public String player1;
    public String player2;
    public ImageButton c11;
    public ImageButton c12;
    public ImageButton c13;
    public ImageButton c21;
    public ImageButton c22;
    public ImageButton c23;
    public ImageButton c31;
    public ImageButton c32;
    public ImageButton c33;
    public Button PlayAgain;
    public Button Home;
    public TextView player1View;
    public TextView player2View;
    int indicator=0;

    ArrayList<Integer> gameTable = new ArrayList<>(
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
    );

    public boolean checkWinner(){
        if(gameTable.get(0).equals(gameTable.get(1)) && gameTable.get(1).equals(gameTable.get(2))){

            return true;
        }

        if(gameTable.get(3).equals(gameTable.get(4)) && gameTable.get(4).equals(gameTable.get(5))){

            return true;
        }

        if(gameTable.get(6).equals(gameTable.get(7)) && gameTable.get(7).equals(gameTable.get(8))){

            return true;
        }

        if(gameTable.get(0).equals(gameTable.get(3)) && gameTable.get(3).equals(gameTable.get(6))){

            return true;
        }

        if(gameTable.get(1).equals(gameTable.get(4)) && gameTable.get(4).equals(gameTable.get(7))){

            return true;
        }

        if(gameTable.get(2).equals(gameTable.get(5)) && gameTable.get(5).equals(gameTable.get(8))){

            return true;
        }

        if(gameTable.get(0).equals(gameTable.get(4)) && gameTable.get(4).equals(gameTable.get(8))){

            return true;
        }

        if(gameTable.get(2).equals(gameTable.get(4)) && gameTable.get(4).equals(gameTable.get(6))){

            return true;
        }

       return false;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_game);

        Intent i=getIntent();

        player1=i.getStringExtra("player1Name");
        player2=i.getStringExtra("player2Name");

        if(player1 == null){
            player1=" Player 1";
        }
        else if(player2 == null){
            player2=" Player 2";
        }

        player1View=findViewById(id.viewPlayer1);
        player2View=findViewById(id.viewPlayer2);

        player1View.setText(player1);
        player2View.setText(player2);
        player1View.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

        c11=findViewById(id.T11);
        c12=findViewById(id.T12);
        c13=findViewById(id.T13);
        c21=findViewById(id.T21);
        c22=findViewById(id.T22);
        c23=findViewById(id.T23);
        c31=findViewById(id.T31);
        c32=findViewById(id.T32);
        c33=findViewById(id.T33);

        PlayAgain=findViewById(id.PlayAgainButton);
        Home=findViewById(id.Homebutton);


    }

    public void stringHighlighting(int indic){
        if(indic % 2 ==0){
            player1View.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
            player2View.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        }
        else{
            player2View.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
            player1View.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }
    }

    public void setBackground(View view,int indic){
        ImageButton temp= (ImageButton) view;

        if(indic % 2 ==0 ){
            temp.setBackgroundResource(drawable.xletter);
        }
        else{
            temp.setBackgroundResource(drawable.oletter);
        }
    }

    public void goHome(View view){
        Intent home=new Intent(this, MainActivity.class);
        startActivity(home);
    }

    public void playAgain(View view){
        Intent reLaunch= new Intent(this, GameActivity.class);
        reLaunch.putExtra("player1Name", player1);
        reLaunch.putExtra("player2Name", player2);
        startActivity(reLaunch);
    }

    public void playClick(View v){

        if(!gameTable.contains(Integer.valueOf(v.getContentDescription().toString()))){
            Toast.makeText(this, "Please select a valid place. Play Again ", Toast.LENGTH_SHORT).show();
        }
        else{
            gameTable.set(Integer.parseInt(v.getContentDescription().toString())-1, indicator%2);
            setBackground(v ,indicator);
            if(checkWinner()){

                PlayAgain.setVisibility(View.VISIBLE);
                Home.setVisibility(View.VISIBLE);

                if(indicator %2 == 0){
                    Toast.makeText(this, player1 + " is the winner !!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this, player2 + " is the winner !!", Toast.LENGTH_LONG).show();
                }
            }
            else{
                if(indicator==8){
                    Toast.makeText(this, " The game has finished on Draw ", Toast.LENGTH_LONG).show();
                }
                indicator++;
                stringHighlighting(indicator);
            }
        }
    }

}