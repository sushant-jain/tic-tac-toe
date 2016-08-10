package com.sushant.zerokata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {
    public static final String player="player";
    Button p1;
    Button p2;
    Button online;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        p1= (Button) findViewById(R.id.player1);
        p2= (Button) findViewById(R.id.player2);
        online= (Button) findViewById(R.id.playerOnline);

        final Intent intent=new Intent(this, MainActivity.class);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(player,1);
                startActivity(intent);
            }
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(player,2);
                startActivity(intent);
            }
        });
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this,SignInActivity.class));
            }
        });
    }
}
