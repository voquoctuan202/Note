package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView tkb;
    ArrayList<String> arrayTKB;
    EditText addTkb;
    Button create;
    TextView textView;
    public static DatabaseMonHoc databaseMonHoc;
    public static DatabaseHinhAnh databaseHinhAnh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_app);
        tkb = (ListView) findViewById(R.id.ListViewTKB);
        textView = (TextView)findViewById(R.id.textView);
        arrayTKB = new ArrayList<>();
        addTkb = (EditText) findViewById(R.id.editTextTKB);
        create = (Button) findViewById(R.id.button_createTKB);
        arrayTKB.add("Học kì 1 - Năm 1");
        ArrayAdapter adapterTKB= new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,arrayTKB);
        tkb.setAdapter(adapterTKB);
        databaseMonHoc = new DatabaseMonHoc(this,"QuanliMon.sqlite",null,1);
        databaseMonHoc.QueryData("CREATE TABLE IF NOT EXISTS MonHoc(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenTKB VARCHAR(50),thu VARCHAR(20),tenmon VARCHAR(50))");

        databaseHinhAnh = new DatabaseHinhAnh(this,"QuanliHinh,sqlite",null,1);
        databaseHinhAnh.QueryData("CREATE TABLE IF NOT EXISTS HinhAnh(Id INTEGER PRIMARY KEY AUTOINCREMENT, ghichu VARCHAR(100), hinhanh BLOG)");


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = addTkb.getText().toString();
                arrayTKB.add(s);
                adapterTKB.notifyDataSetChanged();
                addTkb.setText("");
            }
        });
        tkb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("tkb", arrayTKB.get(position));
                startActivity(intent);
            }
        });
        tkb.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        addTkb.setHint("Mời nhap tên cần đổi");
        addTkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayTKB.set(position,addTkb.getText().toString());
            }
        });

    }
    private void Xoa(int position){
        arrayTKB.remove(position);
    }

}
