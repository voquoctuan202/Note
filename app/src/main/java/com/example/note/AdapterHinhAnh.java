package com.example.note;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class AdapterHinhAnh extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<HinhAnh> arrayHinhAnh;

    public AdapterHinhAnh(Context context, int layout, ArrayList<HinhAnh> arrayHinhAnh) {
        this.context = context;
        this.layout = layout;
        this.arrayHinhAnh = arrayHinhAnh;
    }

    @Override
    public int getCount() {
        return arrayHinhAnh.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate( layout, null);

        TextView txtChitiet = (TextView) convertView.findViewById(R.id.textViewChiTietFour);
        TextView txtNgayChup = (TextView) convertView.findViewById(R.id.textViewNgayChup);
        ImageView imgImage = (ImageView) convertView.findViewById(R.id.imageHinhChup);

        HinhAnh hinhAnh = arrayHinhAnh.get(position);
        txtChitiet.setText(hinhAnh.getChitiet());
        txtNgayChup.setText(hinhAnh.getNgaychup());
        imgImage.setImageBitmap(hinhAnh.getImage());

        return convertView;
    }
}
