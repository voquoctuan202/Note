package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    Button back;
    ArrayList<String> arrayList;
    ListView listViewSecond;
    TextView textViewTKB;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        back = (Button) findViewById(R.id.backSecond);
        listViewSecond = (ListView) findViewById(R.id.ListViewSecond);
        textViewTKB = (TextView) findViewById(R.id.textViewTKB);
        arrayList = new ArrayList<>();
        Intent intent = getIntent();
        String s = intent.getStringExtra("tkb");
        textViewTKB.setText(s);
        arrayList.add("Thứ 2");
        arrayList.add("Thứ 3");
        arrayList.add("Thứ 4");
        arrayList.add("Thứ 5");
        arrayList.add("Thứ 6");
        arrayList.add("Thứ 7");

        ArrayAdapter adapter = new ArrayAdapter(SecondActivity.this, android.R.layout.simple_list_item_1,arrayList);
        listViewSecond.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        listViewSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(SecondActivity.this, ThirdActivity.class);
                intent1.putExtra("thu", arrayList.get(position));
                intent1.putExtra("tkb", s);
                startActivity(intent1);
            }
        });
    }
}