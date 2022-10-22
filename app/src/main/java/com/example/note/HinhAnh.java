package com.example.note;

import android.graphics.Bitmap;

public class HinhAnh {
    private String chitiet;
    private String ngaychup;
    private byte[] image;

    public HinhAnh(String chitiet, String ngaychup, byte[] image) {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
