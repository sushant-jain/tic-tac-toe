package com.sushant.zerokata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Sushant on 05-08-2016.
 */
public class OnlineGameActivity extends AppCompatActivity {
    private static final String TAG = "OnlineGameActivity";

    int a[][];
    int chance, count, t, times, p,currChance,prevChance;
    TextView tv[][];
    TextView tvx,tvh;
    Button restart;
    public ArrayList<String> arrayList=new ArrayList<>();
    Button list;

    DatabaseReference root=FirebaseDatabase.getInstance().getReference().getRoot();
    DatabaseReference child;
    DatabaseReference oppchild;
    PlayerPojo oppPP;
    PlayerPojo pp;
    String oppUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = new int[3][3];
        chance = -1;
        currChance=-1;
        count = 1;
        t = 0;
        times = 0;
        tv = new TextView[3][3];
        tvx = (TextView) findViewById(R.id.tvx);
        p=1;
        arrayList=new ArrayList<>();


        list= (Button) findViewById(R.id.listbut);
        list.setVisibility(View.GONE);
        restart = (Button) findViewById(R.id.restart);
        restart.setVisibility(View.GONE);
        tvh= (TextView) findViewById(R.id.header);
        tv[0][0] = (TextView) findViewById(R.id.one);
        tv[0][1] = (TextView) findViewById(R.id.two);
        tv[0][2] = (TextView) findViewById(R.id.three);
        tv[1][0] = (TextView) findViewById(R.id.four);
        tv[1][1] = (TextView) findViewById(R.id.five);
        tv[1][2] = (TextView) findViewById(R.id.six);
        tv[2][0] = (TextView) findViewById(R.id.seven);
        tv[2][1] = (TextView) findViewById(R.id.eight);
        tv[2][2] = (TextView) findViewById(R.id.nine);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = 0;
            }
        }
        tvh.setText("You v/s "+getIntent().getStringExtra("OPPMAIL"));
        //Intent receivedintent=getIntent();
        String uId=getIntent().getStringExtra("UID");
        //oppUID=root.child(uId).child("oppUid").getKey();
        oppUID=getIntent().getStringExtra("OPPUID");
        Log.d(TAG, "onCreate: oppuid=="+oppUID);

        child=root.child(uId);
        child.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pp = dataSnapshot.getValue(PlayerPojo.class);
                //oppUID=pp.getOppUId();
                chance=pp.getChance();
                currChance=pp.getCurrentChance();
                if(chance==currChance){
                    tvx.setText("TURN : YOU");
                }
                else if(currChance==500){

                }else{
                    tvx.setText("TURN : OPPONENT");
                }
                if(pp.getRow()!=-1){
                    setTv(pp.getRow(),pp.getCol(),currChance==500?(prevChance==2?1:2):(currChance==2?1:2));
                    prevChance=currChance;
                }
                if(currChance==500){
                    int x=isOver();
                    if(x==chance){
                        tvx.setText("YOU WIN");
                    }else if(x==-1){
                        tvx.setText("DRAW");
                    }else{
                        tvx.setText("OPPONENT WINS");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        oppchild=root.child(oppUID);
        //oppPP=new PlayerPojo(oppchild.child("email").getKey(),Integer.valueOf(oppchild.child("online").getKey()),Integer.valueOf(oppchild.child("engaged").getKey()));
          oppPP = new PlayerPojo(oppchild.child("email").getKey(),1,1);
        //oppPP= (PlayerPojo) getIntent().getSerializableExtra("oppPOJO");
        Log.d(TAG, "onCreate: oppPP="+oppPP.getEmail());
        oppchild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                oppPP=dataSnapshot.getValue(PlayerPojo.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: click chance=" + currChance);
                if (currChance==chance) {
                    Log.d(TAG, "onClick: if chance");
                    switch (view.getId()) {
                        case R.id.one:
                            Log.d(TAG, "onClick: click one");
                            if (a[0][0] == 0) {
                                setTv(0, 0,chance);
                                setChance(0,0);
                                break;
                            } else {
                                Toast.makeText(OnlineGameActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.two:
                            if (a[0][1] == 0) {
                                setTv(0, 1,chance);
                                setChance(0,1);
                                break;
                            } else {
                                Toast.makeText(OnlineGameActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.three:
                            if (a[0][2] == 0) {
                                setTv(0, 2,chance);
                                setChance(0,2);
                                break;
                            } else {
                                Toast.makeText(OnlineGameActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.four:
                            if (a[1][0] == 0) {
                                setTv(1, 0,chance);

                                setChance(1,0);
                                break;
                            } else {
                                Toast.makeText(OnlineGameActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.five:
                            if (a[1][1] == 0) {
                                setTv(1, 1,chance);
                                setChance(1,1);
                                break;
                            } else {
                                Toast.makeText(OnlineGameActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.six:
                            if (a[1][2] == 0) {
                                setTv(1, 2,chance);
                                setChance(1,2);
                                break;
                            } else {
                                Toast.makeText(OnlineGameActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.seven:
                            if (a[2][0] == 0) {
                                setTv(2, 0,chance);
                                setChance(2,0);
                                break;
                            } else {
                                Toast.makeText(OnlineGameActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.eight:
                            if (a[2][1] == 0) {
                                setTv(2, 1,chance);
                                setChance(2,1);
                                break;
                            } else {
                                Toast.makeText(OnlineGameActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                            }
                        case R.id.nine:
                            if (a[2][2] == 0) {
                                setTv(2, 2,chance);
                                setChance(2,2);
                                break;
                            } else {
                                Toast.makeText(OnlineGameActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }


                    }
                    count++;
                    int x = isOver();

                    if (x != 0) {
                        chance = 100;
                        if (x == chance) {


                                tvx.setText("You Win");
                                arrayList.add("Round "+String.valueOf(times)+" : You");

                        }  else if (x == -1) {
                            tvx.setText("Draw");
                            arrayList.add("Round "+String.valueOf(times)+" : Draw");
                        }else  {


                            tvx.setText("Opponent wins");
                            arrayList.add("Round "+String.valueOf(times)+" : Opponent");

                        }

                    }
                }
            }
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tv[i][j].setOnClickListener(ocl);
            }
        }

    }

    void setTv(int x, int y,int chances) {

        a[x][y] = chances;
        if (chances%2 == 1) {
            tv[x][y].setText("O");
        } else
            tv[x][y].setText("X");

    }

    int isOver() {
        int xx = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == 0)
                    xx++;
            }
        }

        for (int i = 0; i < 3; i++) {
            int x0 = 0, x1 = 0, x2 = 0;
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == 1) {
                    x1++;
                } else if (a[i][j] == 2) {
                    x2++;
                } else if (a[i][j] == 0) {
                    x0++;
                    xx++;
                }
            }
            if (x1 == 3) {
                return 1;
            } else if (x2 == 3) {
                return 2;
            }
        }


        for (int i = 0; i < 3; i++) {
            int x0 = 0, x1 = 0, x2 = 0;
            for (int j = 0; j < 3; j++) {
                if (a[j][i] == 1) {
                    x1++;
                } else if (a[j][i] == 2) {
                    x2++;
                } else if (a[j][i] == 0) {
                    x0++;
                }
            }
            if (x1 == 3) {
                return 1;
            } else if (x2 == 3) {
                return 2;
            }
        }
        int x11 = 0, x22 = 0, x00 = 0;
        for (int i = 0; i < 3; i++) {

            if (a[i][i] == 1) {
                x11++;
            } else if (a[i][i] == 2) {
                x22++;
            } else if (a[i][i] == 0) {
                x00++;
            }
        }
        Log.d(TAG, "isOver: x11=" + x11 + " x22=" + x22 + " x00=" + x00);
        if (x11 == 3) {
            return 1;
        } else if (x22 == 3) {
            return 2;
        }
        int xx1 = 0, xx2 = 0, xx0 = 0;
        for (int i = 0; i < 3; i++) {

            if (a[i][2 - i] == 1) {
                xx1++;
            } else if (a[i][2 - i] == 2) {
                xx2++;
            } else if (a[i][2 - i] == 0) {
                xx0++;
            }
        }
        if (xx1 == 3) {
            return 1;
        } else if (xx2 == 3) {
            return 2;
        }
        if (xx == 0)
            return -1;
        return 0;
    }

    void setChance(int row,int col){
       // currChance=oppPP.getChance();
        currChance=currChance==2?1:2;
        int x=isOver();
        if(x!=0){
            currChance=500;
        }
        //try {
            oppPP.setCurrentChance(currChance);

            oppPP.setRow(row);
            oppPP.setCol(col);
            oppchild.updateChildren(oppPP.gameStartMapper());

            pp.setCurrentChance(currChance);
            pp.setCol(-1);
            pp.setRow(-1);
            child.updateChildren(pp.gameStartMapper());
//        }catch (Exception e){
//            e.printStackTrace();
//        }


    }
}
