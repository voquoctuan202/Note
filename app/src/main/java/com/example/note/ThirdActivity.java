package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    Button back, add;
    TextView textViewThu;
    EditText editADD;
    ListView listView;
    ArrayList<String> arrayListMonHoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        back = (Button) findViewById(R.id.buttonBackThird);
        add = (Button) findViewById(R.id.buttonAddThird);
        textViewThu = (TextView) findViewById(R.id.textViewThird);
        editADD = (EditText) findViewById(R.id.addTextThird);
        listView = (ListView) findViewById(R.id.ListViewThird);
        arrayListMonHoc = new ArrayList<>();

        Intent intent = getIntent();
        String thu  = intent.getStringExtra("thu");
        String tkb = intent.getStringExtra("tkb");
        textViewThu.setText(thu);
        ArrayAdapter adapter = new ArrayAdapter(ThirdActivity.this, android.R.layout.simple_list_item_1,arrayListMonHoc);
        listView.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editADD.getText().toString();
                arrayListMonHoc.add(s);
                adapter.notifyDataSetChanged();
                editADD.setText("");
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1= new Intent(ThirdActivity.this, FourActivity.class);
                intent1.putExtra("tkb",tkb);
                intent1.putExtra("thu",thu);
                intent1.putExtra("tenmon",arrayListMonHoc.get(position));
                startActivity(intent1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(ThirdActivity.this, SecondActivity.class);
                intent1.putExtra("tkb", tkb);
                startActivity(intent1);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ShowMenu(view,position);
                return false;
            }
        });
    }
    private void ShowMenu(View view,int position){
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_doiten: DoiTen(position);
                        break;
                    case R.id.menu_xoa:Xoa(position);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    private void DoiTen(int position){
        editADD.setHint("Mời nhap tên cần đổi");
        editADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListMonHoc.set(position,editADD.getText().toString());
            }
        });

    }
    private void Xoa(int position){
        arrayListMonHoc.remove(position);
    }
}