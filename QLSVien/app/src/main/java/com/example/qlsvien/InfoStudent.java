package com.example.qlsvien;

public class InfoStudent{
    private int id;
    private String name;
    private String namSinh;
    private String lopHoc;
    private String queQuan;
    private String maSV;

    public InfoStudent(int id, String name, String namSinh, String lopHoc, String queQuan, String maSV) {
        this.id = id;
        this.name = name;
        this.namSinh = namSinh;
        this.lopHoc = lopHoc;
        this.queQuan = queQuan;
        this.maSV = maSV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(String lopHoc) {
        this.lopHoc = lopHoc;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", namSinh='" + namSinh + '\'' +
                ", lopHoc='" + lopHoc + '\'' +
                ", queQuan='" + queQuan + '\'' +
                ", maSV='" + maSV + '\'' +
                '}';
    }
}