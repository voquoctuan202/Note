package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Toast;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    Button back, add;
    TextView textViewThu;
    EditText editADD;
    ListView listView;
    ArrayList<String> arrayListMonHoc;
    String thu;
    String tkb;
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
        thu  = intent.getStringExtra("thu");
        tkb = intent.getStringExtra("tkb");


        textViewThu.setText(thu);
        ArrayAdapter adapter = new ArrayAdapter(ThirdActivity.this, android.R.layout.simple_list_item_1,arrayListMonHoc);
        listView.setAdapter(adapter);

        GetDataMonHoc();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editADD.getText().toString();
                MainActivity.databaseMonHoc.QueryData("INSERT INTO MonHoc VALUES(null,'"+ tkb+"','"+thu+"','"+s+"')");
                //arrayListMonHoc.add(s);
                adapter.notifyDataSetChanged();
                editADD.setText("");
                GetDataMonHoc();
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

                ShowMenu(view,tkb,thu,arrayListMonHoc.get(position));
                return false;
            }
        });
    }
    private void ShowMenu(View view,String tentkb,String day,String mon){
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_doiten: DoiTen(tentkb,day,mon);
                        break;
                    case R.id.menu_xoa:Xoa(tentkb,day,mon);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    private void DoiTen(String tentkb, String day,String mon){
        editADD.setHint("Mời nhap tên cần đổi");
        editADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.databaseMonHoc.QueryData("UPDATE MonHoc SET tenmon = '"+editADD.getText().toString()+"' WHERE tenTKB= '"+ tentkb+"' && thu='"+day+"' && tenmon= '"+mon+"'");
                Toast.makeText(ThirdActivity.this,"Da sua ten",Toast.LENGTH_SHORT).show();
                GetDataMonHoc();
            }
        });

    }
    private void Xoa(String tentkb, String day,String mon){
        MainActivity.databaseMonHoc.QueryData("DELETE FROM MonHoc WHERE tenTKB= '"+ tentkb+"' && thu='"+day+"' && tenmon= '"+mon+"'");
    }
    private void GetDataMonHoc(){
        Cursor cursor = MainActivity.databaseMonHoc.getData("SELECT * FROM MonHoc");
        arrayListMonHoc.clear();
        while (cursor.moveToNext()){
            String tentkb = cursor.getString(1);
            String day = cursor.getString(2);
            String tenmon = cursor.getString(3);
            if(tentkb.equals(tkb) && day.equals(thu))
                arrayListMonHoc.add(tenmon);
        }
    }

}

