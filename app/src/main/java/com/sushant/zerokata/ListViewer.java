package com.sushant.zerokata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Intent receivedList=getIntent();
        ArrayList<String> arrayList=receivedList.getStringArrayListExtra("list");
        ListView lv= (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.listlayout,arrayList);
        lv.setAdapter(adapter);

    }
}
