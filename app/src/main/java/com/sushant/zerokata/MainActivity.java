package com.sushant.zerokata;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "123";
    private int a[][];
    private int chance, count, t, times, p;
    private TextView tv[][];
    private TextView tvx;
    private Button restart;
    public ArrayList<String> arrayList=new ArrayList<>();
    private Button list;

//    public MainActivity() {
//        a=new int[3][3];
//        chance=1;
//        count=1;
//        t=0;
//        tv=new TextView[3][3];
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = new int[3][3];
        chance = 2;
        count = 1;
        t = 0;
        times = 0;
        tv = new TextView[3][3];
        tvx = (TextView) findViewById(R.id.tvx);
        p=1;
        arrayList=new ArrayList<>();

        Intent received=getIntent();
        p=received.getIntExtra(HomeScreen.player,1);

        // setView();
        list= (Button) findViewById(R.id.listbut);
        restart = (Button) findViewById(R.id.restart);
        tv[0][0] = (TextView) findViewById(R.id.one);
        tv[0][1] = (TextView) findViewById(R.id.two);
        tv[0][2] = (TextView) findViewById(R.id.three);
        tv[1][0] = (TextView) findViewById(R.id.four);
        tv[1][1] = (TextView) findViewById(R.id.five);
        tv[1][2] = (TextView) findViewById(R.id.six);
        tv[2][0] = (TextView) findViewById(R.id.seven);
        tv[2][1] = (TextView) findViewById(R.id.eight);
        tv[2][2] = (TextView) findViewById(R.id.nine);
        Log.d(TAG, "onCreate: views atached ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = 0;
            }
        }
        if(p==1) tvx.setText("Your Turn");
        else if(chance==2) tvx.setText("Player 1 Turn");
        else tvx.setText("Player 2 Turn");
        if(p==2){
            TextView tvHeader= (TextView) findViewById(R.id.header);
            tvHeader.setText("Two Player");
        }

        final Intent listIntent=new Intent(this,ListViewer.class);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listIntent.putExtra("list",arrayList);
                startActivity(listIntent);

            }
        });


        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: click chance=" + chance);
                if ((p == 1 && chance == 2) || p == 2) {
                    Log.d(TAG, "onClick: if chance");
                    switch (view.getId()) {
                        case R.id.one:
                            Log.d(TAG, "onClick: click one");
                            if (a[0][0] == 0) {
                                setTv(0, 0);
                                setChance();
                                break;
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.two:
                            if (a[0][1] == 0) {
                                setTv(0, 1);
                                setChance();
                                break;
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.three:
                            if (a[0][2] == 0) {
                                setTv(0, 2);
                                setChance();
                                break;
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.four:
                            if (a[1][0] == 0) {
                                setTv(1, 0);
                                setChance();
                                break;
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.five:
                            if (a[1][1] == 0) {
                                setTv(1, 1);
                                setChance();
                                break;
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.six:
                            if (a[1][2] == 0) {
                                setTv(1, 2);
                                setChance();
                                break;
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.seven:
                            if (a[2][0] == 0) {
                                setTv(2, 0);
                                setChance();
                                break;
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        case R.id.eight:
                            if (a[2][1] == 0) {
                                setTv(2, 1);
                                setChance();
                                break;
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                            }
                        case R.id.nine:
                            if (a[2][2] == 0) {
                                setTv(2, 2);
                                setChance();
                                break;
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong Move", Toast.LENGTH_SHORT).show();
                                break;
                            }


                    }
                    count++;
                    int x = isOver();
                    if (x == 0 && chance == 1) {
                        tvx.setText("Jarvis' Turn");
                        Waiter.Listener listener = new Waiter.Listener() {
                            @Override
                            public void myfunc() {
                                compChance();
                                chance = 2;
                                tvx.setText("Your Turn");
                                count++;
                                int xx = isOver();
                                if (xx != 0) {
                                    chance = 100;
                                    if (xx == 1) {
                                        tvx.setText("Jarvis Wins");
                                        arrayList.add("Round "+String.valueOf(times)+" : Jarvis");
                                    } else if (xx == 2) {
                                        tvx.setText("pappu wins");
                                        arrayList.add("Round "+String.valueOf(times)+" : Pappu");
                                    } else if (xx == -1) {
                                        tvx.setText("Draw");
                                        arrayList.add("Round "+String.valueOf(times)+" : Draw");
                                    }

                                }
                            }
                        };
                        Waiter wait = new Waiter(listener);
                        wait.execute();
                    }
                    if (x != 0) {
                        chance = 100;
                        if (x == 1) {
                            if (p==1){
                                tvx.setText("Jarvis Wins");
                                arrayList.add("Round "+String.valueOf(times)+" : Jarvis");
                            }
                            else{
                                tvx.setText("Player 2 wins");
                                arrayList.add("Round "+String.valueOf(times)+" : Player 2");
                            }
                        } else if (x == 2) {
                            if(p==1){
                                tvx.setText("pappu wins");
                                arrayList.add("Round "+String.valueOf(times)+" : Pappu");
                            }
                            else{
                                tvx.setText("Player 1 wins");
                                arrayList.add("Round "+String.valueOf(times)+" : Player 1");
                            }
                        } else if (x == -1) {
                            tvx.setText("Draw");
                            arrayList.add("Round "+String.valueOf(times)+" : Draw");
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
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        a[i][j] = 0;
                        tv[i][j].setText(null);
                    }
                }
                times++;
                chance = times % 2;
                if (chance == 0)
                    chance = 2;
                if(chance==1&&p==2)
                    chance=3;
                count = 1;
                if(p==1) tvx.setText("Your Turn");
                else if(chance==2) tvx.setText("Player 1 Turn");
                else tvx.setText("Player 2 Turn");
                t = 0;
                if (chance == 1) {
                    compChance();
                    chance = 2;
                    count++;
                }
            }
        });
//        while(isOver()==0){
//            Log.d(TAG, "onCreate: in while");
//            if(chance==1){
//                compChance();
//                chance=2;
//            }
//        }


    }

//    void setView() {
//        tv[0][0].findViewById(R.id.one);
//        tv[0][1].findViewById(R.id.two);
//        tv[0][2].findViewById(R.id.three);
//        tv[1][0].findViewById(R.id.four);
//        tv[1][1].findViewById(R.id.five);
//        tv[1][2].findViewById(R.id.six);
//        tv[2][0].findViewById(R.id.seven);
//        tv[2][1].findViewById(R.id.eight);
//        tv[2][2].findViewById(R.id.nine);
//    }

    void setTv(int x, int y) {
        int zz=-1;
        if(chance==1||chance==3) zz=1;
        else zz=2;
        a[x][y] = zz;
        if (chance%2 == 1) {
            tv[x][y].setText("O");
        } else
            tv[x][y].setText("X");

    }

    void compChance() {
        Log.d(TAG, "compChance: Started");
        for (int i = 0; i < 3; i++) {
            int x0 = 0, x1 = 0, x2 = 0;
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == 1) {
                    x1++;
                } else if (a[i][j] == 2) {
                    x2++;
                } else if (a[i][j] == 0) {
                    x0++;
                }
            }
            if ((x1 == 2) && x0 > 0) {
                for (int j = 0; j < 3; j++) {
                    if (a[i][j] == 0) {
                        setTv(i, j);
                        return;
                    }
                }
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
                }
            }
            if ((x2 == 2) && x0 > 0) {
                for (int j = 0; j < 3; j++) {
                    if (a[i][j] == 0) {
                        setTv(i, j);
                        return;
                    }
                }
            }
        }
        Log.d(TAG, "compChance: row check done");
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
            if ((x1 == 2) && x0 > 0) {
                for (int j = 0; j < 3; j++) {
                    if (a[j][i] == 0) {
                        setTv(j, i);
                        return;
                    }
                }
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
            if ((x2 == 2) && x0 > 0) {
                for (int j = 0; j < 3; j++) {
                    if (a[j][i] == 0) {
                        setTv(j, i);
                        return;
                    }
                }
            }
        }
        Log.d(TAG, "compChance: column check done");
        int xx1 = 0, xx2 = 0, xx0 = 0;
        for (int i = 0; i < 3; i++) {

            if (a[i][i] == 1) {
                xx1++;
            } else if (a[i][i] == 2) {
                xx2++;
            } else if (a[i][i] == 0) {
                xx0++;
            }
        }
        if ((xx1 == 2 || xx2 == 2)) {
            for (int j = 0; j < 3; j++) {
                if (a[j][j] == 0) {
                    setTv(j, j);
                    return;
                }
            }
        }


        Log.d(TAG, "compChance: major diagonal checked");
        int x11 = 0, x22 = 0, x00 = 0;
        for (int i = 0; i < 3; i++) {

            if (a[i][2 - i] == 1) {
                x11++;
            } else if (a[i][2 - i] == 2) {
                x22++;
            } else if (a[i][2 - i] == 0) {
                x00++;
            }
        }
        if ((x11 == 2 || x22 == 2)) {
            for (int j = 0; j < 3; j++) {
                if (a[j][2 - j] == 0) {
                    setTv(j, 2 - j);
                    return;
                }
            }
        }


        Log.d(TAG, "compChance: minor diagonal checked");
        if (count == 1) {
            setTv(0, 0);
            return;
        } else if (count == 2) {
            if (a[1][1] == 0) {
                setTv(1, 1);
                return;
            } else {
                setTv(0, 0);
                return;
            }
        } else if (count == 3) {
            if (a[0][1] == 2 || a[0][2] == 2 || a[2][1] == 2 || a[2][2] == 2) {
                setTv(2, 0);
                t = 1;
                return;
            } else if (a[1][0] == 2 || a[2][0] == 2 || a[1][2] == 2) {
                setTv(0, 2);
                t = 2;
                return;
            } else {
                setTv(2, 2);
                return;
            }
        } else if (count == 4) {     //TODO:create a proper algo for move 4
//            if (a[0][1] == 0 && a[2][1] == 0) {
//                setTv(0, 1);
//                return;
//            } else if (a[1][1] == 0) {
//                setTv(2, 2);
//                return;
//            } else if (a[1][0] == 0) {
//                setTv(1, 0);
//                return;
//            }
            if (a[1][1] == 2 && a[2][2] == 2) {
                setTv(0, 2);
                return;
            } else if (a[0][1] == 0 && a[2][1] == 0) {
                setTv(0, 1);
                return;
            } else if (a[1][0] == 0 && a[1][2] == 0) {
                setTv(1, 0);
                return;
            } else if (a[1][0] == 2 && a[2][1] == 2) {
                setTv(2, 0);
                return;
            } else {
                setTv(0, 2);
                return;
            }
        } else if (count == 5) {
            int ii = 0, jj = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (a[i][j] == 1) {
                        ii = i;
                        jj = j;
                    }
                }
            }
            if (a[0][2] == 0 && a[0][1] == 0 && a[ii / 2][(jj + 2) / 2] == 0) {
                setTv(0, 2);
                return;
            } else if (a[2][2] == 0 && a[1][1] == 0 && a[(ii + 2) / 2][(jj + 2) / 2] == 0) {
                setTv(2, 2);
                return;
            } else if (a[2][0] == 0 && a[1][0] == 0 && a[(ii + 2) / 2][(jj) / 2] == 0) {
                setTv(2, 0);
                return;
            }
        } else {
            if (a[1][1] == 1 && a[0][0] == 0 && a[2][2] == 0) {
                if (a[1][0] == 0 || a[0][1] == 0) {
                    setTv(0, 0);
                    return;
                } else {
                    setTv(2, 2);
                    return;
                }
            } else if (a[1][1] == 1 && a[0][2] == 0 && a[2][0] == 0) {
                if (a[1][0] == 0 || a[2][1] == 0) {
                    setTv(2, 0);
                    return;
                } else {
                    setTv(0, 2);
                    return;
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    int x0 = 0, x1 = 0;
                    for (int j = 0; j < 3; j++) {
                        if (a[i][j] == 0) {
                            x0++;
                        } else if (a[i][j] == 1) {
                            x1++;
                        }
                    }
                    if (x1 == 1 && x0 == 2) {
                        for (int j = 0; j < 3; j++) {
                            if (a[i][j] == 0) {
                                setTv(i, j);
                                return;
                            }
                        }
                    }
                }
                for (int i = 0; i < 3; i++) {
                    int x0 = 0, x1 = 0;
                    for (int j = 0; j < 3; j++) {
                        if (a[j][i] == 0) {
                            x0++;
                        } else if (a[j][i] == 1) {
                            x1++;
                        }
                    }
                    if (x1 == 1 && x0 == 2) {
                        for (int j = 0; j < 3; j++) {
                            if (a[j][i] == 0) {
                                setTv(j, i);
                                return;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (a[i][j] == 0) {
                        setTv(i, j);
                        return;
                    }
                }
            }
        }
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
    void setChance(){
        if (p == 1) {
            chance = 1;
        } else {
            if (chance == 2) {
                chance = 3;
                tvx.setText("Player 2 Turn");
            } else {
                chance = 2;
                tvx.setText("Player 1 Turn");
            }
        }
    }
}
