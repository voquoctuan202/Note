package com.example.note;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FourActivity extends AppCompatActivity {
    Button back, add, addHINH_ANH;
    TextView TenMon, GhiChu;
    EditText editMon;
    ArrayList<HinhAnh> arrayHinhanh ;
    int REQUEST_CODE_CAMERA = 123;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        listView = (ListView) findViewById(R.id.listviewHINHANH);
        addHINH_ANH = (Button) findViewById(R.id.buttonAddIMG);
        back = (Button) findViewById(R.id.buttonBackFour);
        add = (Button) findViewById(R.id.buttonAddFour);
        TenMon = (TextView) findViewById(R.id.textViewTenMon);
        GhiChu = (TextView) findViewById(R.id.textViewGhiChu);
        editMon = (EditText) findViewById(R.id.EditTextChiTiet);
        arrayHinhanh = new ArrayList<>();

        AdapterHinhAnh adapter = new AdapterHinhAnh(FourActivity.this, R.layout.hinh_anh,arrayHinhanh);
        listView.setAdapter(adapter);
        MonHoc monHoc = new MonHoc();
        Intent intent =getIntent();
        String tkb = intent.getStringExtra("tkb");
        String thu = intent.getStringExtra("thu");
        String tenmon = intent.getStringExtra("tenmon");
        ADD_MON(monHoc,tkb,thu,tenmon);

        GhiChu.setText(tkb+ "\n" +thu +"\n"+ tenmon);
        TenMon.setText(tenmon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(FourActivity.this, ThirdActivity.class);
                intent1.putExtra("tkb",monHoc.getTenTKB());
                intent1.putExtra("thu",monHoc.getThu());
                startActivity(intent1);
            }
        });
        addHINH_ANH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(FourActivity.this,
                        new String[]{Manifest.permission.CAMERA},REQUEST_CODE_CAMERA);
                adapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogChiTiet(position,adapter);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GhiChu.append("\n" + editMon.getText());
            }
        });
    }
    private void ADD_MON(MonHoc monHoc, String tkb, String thu, String tenmon){
        monHoc.setTenTKB(tkb);
        monHoc.setThu(thu);
        monHoc.setTenmon(tenmon);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_CAMERA && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,REQUEST_CODE_CAMERA);
        }else Toast.makeText(FourActivity.this, "K cho mo camera", Toast.LENGTH_SHORT).show();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            //chuyen bitmap sang byte[]
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream(); // khoi tao bien byteArray de luu anh keu byte[]
            bitmap.compress(Bitmap.CompressFormat.PNG,100 /*so cang nho cang chat luong*/,byteArray); // chuyen doi kieu tu bitmap sang kieu byte[] dong thoi gan cho byteArray
            byte[] hinhanh = byteArray.toByteArray(); //chuyen du lieu sang file hinh anh
            arrayHinhanh.add(new HinhAnh(" ",DayNow(), hinhanh));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String DayNow(){
        Calendar calendar = Calendar.getInstance();
        int dayN = calendar.get(Calendar.DATE);
        int monthN = calendar.get(Calendar.MONTH);
        int yearN = calendar.get(Calendar.YEAR);
        String s = dayN+ "/"+ (monthN+1)+ "/"+ yearN;
        return s;
    }
    private void DialogChiTiet(int position, AdapterHinhAnh adapterHinhAnh){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);// khong cho hien title
        dialog.setContentView(R.layout.chi_tiet);

        Button buttonAcp = (Button) dialog.findViewById(R.id.buttonDialogAcp);
        EditText editText = (EditText) dialog.findViewById(R.id.editDialogChiTiet);
        Button buttonChose = (Button) dialog.findViewById(R.id.buttonChose);

        buttonChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HinhAnh hinhAnh = arrayHinhanh.get(position);
                hinhAnh.setChitiet(editText.getText().toString());
                arrayHinhanh.set(position, hinhAnh);
                adapterHinhAnh.notifyDataSetChanged();
                dialog.cancel();
            }
        });
        buttonAcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}