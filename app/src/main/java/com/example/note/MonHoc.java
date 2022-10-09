package com.example.note;

import java.io.Serializable;

public class MonHoc implements Serializable {
    private String tenTKB, thu, tenmon, tiethoc, phonghoc, ghichu;

    public MonHoc() {
        this.tenTKB = tenTKB;
        this.thu = thu;
        this.tenmon = tenmon;
        this.tiethoc = tiethoc;
        this.phonghoc = phonghoc;
        this.ghichu = ghichu;
    }
    public MonHoc(String tenTKB, String thu, String tenmon, String tiethoc, String phonghoc, String ghichu) {
        this.tenTKB = tenTKB;
        this.thu = thu;
        this.tenmon = tenmon;
        this.tiethoc = tiethoc;
        this.phonghoc = phonghoc;
        this.ghichu = ghichu;
    }

    public String getTenTKB() {
        return tenTKB;
    }

    public void setTenTKB(String tenTKB) {
        this.tenTKB = tenTKB;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getTiethoc() {
        return tiethoc;
    }

    public void setTiethoc(String tiethoc) {
        this.tiethoc = tiethoc;
    }

    public String getPhonghoc() {
        return phonghoc;
    }

    public void setPhonghoc(String phonghoc) {
        this.phonghoc = phonghoc;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
