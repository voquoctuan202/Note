package com.example.note;

import java.io.Serializable;

public class MonHoc implements Serializable {
    private String tenTKB, thu, tenmon;

    public MonHoc() {
        this.tenTKB = tenTKB;
        this.thu = thu;
        this.tenmon = tenmon;
    }

    public MonHoc(String tenTKB, String thu, String tenmon, String tiethoc, String phonghoc, String ghichu) {
        this.tenTKB = tenTKB;
        this.thu = thu;
        this.tenmon = tenmon;

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
}


