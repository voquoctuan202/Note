package com.example.note;

import android.graphics.Bitmap;

public class HinhAnh {
    private String chitiet;
    private String ngaychup;
    private Bitmap image;

    public HinhAnh(String chitiet, String ngaychup, Bitmap image) {
        this.chitiet = chitiet;
        this.ngaychup = ngaychup;
        this.image = image;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public String getNgaychup() {
        return ngaychup;
    }

    public void setNgaychup(String ngaychup) {
        this.ngaychup = ngaychup;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
